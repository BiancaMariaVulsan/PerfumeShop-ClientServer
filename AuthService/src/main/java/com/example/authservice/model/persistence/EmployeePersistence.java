package com.example.authservice.model.persistence;

import com.example.authservice.model.Employee;
import com.example.authservice.model.Person;
import com.example.authservice.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeePersistence extends DatabaseObj<Employee> {

    protected String createInsertQuery(Employee employee) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO person (firstname,lastname,role,username,password,id_shop) VALUES (");
        sb.append("'");
        sb.append(employee.getFirstName());sb.append("','");
        sb.append(employee.getLastName());sb.append("',");
        sb.append(employee.getRole().ordinal());sb.append(",'");
        sb.append(employee.getUsername());sb.append("','");
        sb.append(employee.getPassword());sb.append("',");
        sb.append(employee.getShopId());
        sb.append(")");

        return sb.toString();
    }

    public boolean checkIfObjectAlreadyExists(String username) {
        String query = "SELECT COUNT (*) AS counter FROM person WHERE username = \'"  + username + "\'";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int count = 0;
        try {
            if(resultSet.next()){
                count = resultSet.getInt("counter");
            }
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        } finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return count == 0;
    }

    protected String createUpdateQuery(Employee person) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE person SET firstname='");
        sb.append(person.getFirstName());
        sb.append("',lastname='");
        sb.append(person.getLastName());
        sb.append("',username='");
        sb.append(person.getUsername());
        sb.append("',password='");
        sb.append(person.getPassword());
        sb.append("',role=");
        sb.append(person.getRole().ordinal());
        sb.append(",id_shop=");
        sb.append(person.getShopId());
        sb.append(" WHERE id=");
        sb.append(person.getId());
        return sb.toString();
    }

    protected String createSelectQuery(String field) {
        return "SELECT * FROM person WHERE role=" + Role.EMPLOYEE.ordinal();
    }

    protected List<Employee> createObjects(ResultSet resultSet) {
        try{
            List<Employee> persons = new ArrayList<>();
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int roleNr = resultSet.getInt("role");
                Role role = Role.values()[roleNr];
                if(role.equals(Role.EMPLOYEE)) {
                    int idShop = resultSet.getInt("id_shop");
                    Employee person = new Employee(id, firstName, lastName, username, password, idShop);
                    persons.add(person);
                }
            }
            return persons;
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
