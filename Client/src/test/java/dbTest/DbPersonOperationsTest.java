package dbTest;

import com.example.perfumeshop.model.Person;
import com.example.perfumeshop.model.Role;
import com.example.perfumeshop.model.persistence.PersonPersistence;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DbPersonOperationsTest {
    PersonPersistence personPersistence = new PersonPersistence();

    @Test
    public void insertTest() {
        Person person = new Person("Anca", "Muscalagiu", Role.EMPLOYEE, "anca", "anca");
        personPersistence.insert(person);
        Person dbPerson = personPersistence.findAll().stream().filter(p -> p.getUsername().equals(person.getUsername())).findFirst().orElse(null);
        assertTrue(dbPerson != null);
    }
    @Test
    public void readTest() {
        List<Person> dbPerson = personPersistence.findAll();
        assertTrue(dbPerson.size() > 0);
    }
    @Test
    public void updateTest() {
        Person person = new Person("Brianna", "Gray", Role.EMPLOYEE, "brianna", "brianna");
        personPersistence.insert(person);
        Person insertedPerson = personPersistence.findAll().stream().filter(p -> person.getUsername().equals(p.getUsername())).findFirst().orElse(null);
        if(insertedPerson != null) {
            int idInsertedPerson = insertedPerson.getId();
            Person newPerson = new Person(idInsertedPerson, "Briannaaaa", "Gray", Role.EMPLOYEE, "brianna", "brianna");
            personPersistence.update(newPerson);
            Person dbPerson = personPersistence.findAll().stream().filter(p -> newPerson.getUsername().equals(p.getUsername())).toList().get(0);
            assertTrue(dbPerson.equals(newPerson));
        }
    }
    @Test
    public void deleteTest() {
        // delete the first person from the db
        Person person = personPersistence.findAll().get(0);
        personPersistence.delete(person);
        Person dbPerson = personPersistence
                .findAll()
                .stream()
                .filter(p -> person.getUsername().equals(p.getUsername()))
                .findFirst()
                .orElse(null);
        assertTrue(dbPerson == null);
    }
}
