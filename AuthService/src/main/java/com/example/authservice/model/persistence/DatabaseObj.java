package com.example.authservice.model.persistence;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseObj<T> {
    protected static final Logger LOGGER = Logger.getLogger(DatabaseObj.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public DatabaseObj(){
        this.type = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        }catch(SQLException e){
            LOGGER.log(Level.WARNING, e.getMessage());
            return null;
        }finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    protected List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        try {
            while (resultSet.next()) {
                T instance = type.getDeclaredConstructor().newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException | SecurityException | IllegalArgumentException | SQLException | IntrospectionException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(null);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        }catch(SQLException e){
            LOGGER.log(Level.WARNING, e.getMessage());
            return null;
        }finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public void insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        }catch(SQLException e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally{
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public boolean update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        }catch(SQLException e){
            LOGGER.log(Level.WARNING, e.getMessage());
            return false;
        }finally{
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return true;
    }

    public void delete(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Field idField = t.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            String query = createDeleteQuery(t, idField);
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        }catch(SQLException | NoSuchFieldException e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally{
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    protected String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("*");
        sb.append(" FROM ");
        sb.append(type.getSimpleName().toLowerCase(Locale.ROOT)); // we consier that the name of the table is the same as the name of the class
        if(field != null) {
            sb.append(" WHERE " + field + " =?");
        }
        return sb.toString();
    }

    private String propertyToString(T t, Field field){
        String strVal = "";
        try {
            Field privateField = t.getClass().getDeclaredField(field.getName());
            privateField.setAccessible(true);
            if(field.getType().isAssignableFrom(StringProperty.class)){

                StringProperty stringProperty = (StringProperty) privateField.get(t);
                strVal = strVal + stringProperty.getValue();
            }
            else if(field.getType().isAssignableFrom(DoubleProperty.class)){
                DoubleProperty floatProperty = (DoubleProperty)privateField.get(t);
                strVal = strVal + floatProperty.getValue().floatValue();
            }
            else if(field.getType().isAssignableFrom(IntegerProperty.class)){
                IntegerProperty integerProperty = (IntegerProperty)privateField.get(t);
                strVal = strVal + integerProperty.getValue();

            } else if(field.getType().isAssignableFrom(BooleanProperty.class)){
                BooleanProperty booleanProperty = (BooleanProperty)privateField.get(t);
                strVal = strVal + booleanProperty.getValue();
            }
        } catch (IllegalArgumentException |  IllegalAccessException | SecurityException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return strVal;
    }

    protected void appendField(T t, Field field, StringBuilder sb){
        if(field.getType().isAssignableFrom(StringProperty.class) || field.getType().isAssignableFrom(BooleanProperty.class)){
            sb.append("\'");
            sb.append(propertyToString(t, field));
            sb.append("\'");
        }
        else{
            sb.append(propertyToString(t, field));
        }
    }

    protected String createInsertQuery(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append("(");

        for(Field field:this.type.getDeclaredFields()){
            if(field.getName() != "id"){
                sb.append(field.getName() + ",");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(") VALUES(");
        for(Field field:t.getClass().getDeclaredFields()){
            if(field.getName() != "id"){
                this.appendField(t, field, sb);
                sb.append(",");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");

        return sb.toString();
    }

    protected String createUpdateQuery(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");

        for(Field field:this.type.getDeclaredFields()){
            if(!field.getName().equals("id")){
                sb.append(field.getName() + "=");
                this.appendField(t, field, sb);
                sb.append(",");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        for(Field field:this.type.getDeclaredFields()) {
            if (field.getName().equals("id")) {
                sb.append(" WHERE id = ");
                this.appendField(t, field, sb);
                break;
            }
        }
        return sb.toString();
    }

    private String createDeleteQuery(T t, Field field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field.getName() + " =");
        try {
            IntegerProperty integerProperty = (IntegerProperty)field.get(t);
            sb.append(integerProperty.getValue().intValue());
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
