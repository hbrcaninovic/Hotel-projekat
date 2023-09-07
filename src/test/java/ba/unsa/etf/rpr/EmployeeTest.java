package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.domain.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
public class EmployeeTest {

    private Employee employee;

    @BeforeEach
    public void initialize(){
        employee = new Employee(1,
                "username",
                "password",
                "name",
                "lastname",
                "test@mail.com",
                "job",
                1000,
                0);
    }

    @Test
    public void testGetters(){
        assertAll("Test",
                () -> assertEquals(1,employee.getId()),
                () -> assertEquals("username",employee.getUsername()),
                () -> assertEquals("password",employee.getPassword()),
                () -> assertEquals("name",employee.getFirst_name()),
                () -> assertEquals("lastname",employee.getLast_name()),
                () -> assertEquals("test@mail.com", employee.getEmail()),
                () -> assertEquals("job",employee.getJob_title()),
                () -> assertEquals(1000.0,employee.getSalary()),
                () -> assertEquals(0,employee.getAdmin())
        );
    }

    @Test
    public void testSetters(){
        employee.setId(2);
        employee.setUsername("username2");
        employee.setPassword("password2");
        employee.setFirst_name("name2");
        employee.setLast_name("lastname2");
        employee.setEmail("test@mail.com2");
        employee.setJob_title("job2");
        employee.setSalary(2000);
        employee.setAdmin(1);

        assertAll("Test",
                () -> assertEquals(2,employee.getId()),
                () -> assertEquals("username2",employee.getUsername()),
                () -> assertEquals("password2",employee.getPassword()),
                () -> assertEquals("name2",employee.getFirst_name()),
                () -> assertEquals("lastname2",employee.getLast_name()),
                () -> assertEquals("test@mail.com2",employee.getEmail()),
                () -> assertEquals("job2",employee.getJob_title()),
                () -> assertEquals(2000.0,employee.getSalary()),
                () -> assertEquals(1,employee.getAdmin())
                );
    }

    @Test
    public void testEmptyConstructorAndSetters(){
        employee = new Employee();

        assertAll("Test null",
                () -> assertEquals(0,employee.getId()),
                () -> assertNull(employee.getUsername()),
                () -> assertNull(employee.getPassword()),
                () -> assertNull(employee.getFirst_name()),
                () -> assertNull(employee.getLast_name()),
                () -> assertNull(employee.getEmail()),
                () -> assertNull(employee.getJob_title()),
                () -> assertEquals(0.0,employee.getSalary()),
                () -> assertEquals(0,employee.getAdmin())
        );

        employee.setId(1);
        employee.setUsername("username");
        employee.setAdmin(1);

        assertAll("Test setters",
                () -> assertEquals(1,employee.getId()),
                () -> assertEquals("username",employee.getUsername()),
                () -> assertEquals(1,employee.getAdmin())
        );
    }

    @Test
    public void testEequals(){
        Employee employee2 = new Employee();
        employee2.setId(1);
        employee2.setUsername("username");
        employee2.setPassword("password");
        employee2.setFirst_name("name");
        employee2.setLast_name("lastname");
        employee2.setEmail("test@mail.com");
        employee2.setJob_title("job");
        employee2.setSalary(1000);
        employee2.setAdmin(0);

        assertEquals(employee, employee2);
        assertEquals(employee.toString(),employee2.toString());
    }

    @Test
    public void testEmployeeHashCode(){
        employee = new Employee();
        assertNotEquals(Objects.hash(employee),employee.hashCode());
    }

}
