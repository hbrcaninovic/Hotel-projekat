package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeManagerTest {

    private final List<Employee> employees = new ArrayList<>();
    private Employee employee;
    private final EmployeeManager employeeManager = Mockito.mock(EmployeeManager.class);

    @BeforeEach
    public void initialize()
    {
        employee = new Employee(1,
                "username",
                "password",
                "name",
                "lastname",
                "test@mail.com",
                "job",
                1000,
                0);

        employees.add(employee);
        employee.setId(2);
        employee.setFirst_name("name2");

        employees.add(employee);
    }

    @Test
    public void testGetAllEmployees() throws HotelExceptions {
        Mockito.when(employeeManager.getAllEmployees()).thenReturn(employees);
        assertEquals(employees,employeeManager.getAllEmployees());
    }

    @Test
    public void testAddEmployees() throws HotelExceptions {
        Mockito.when(employeeManager.getAllEmployees()).thenReturn(employees);
        Mockito.doCallRealMethod().when(employeeManager).addEmployee(employee);

        List<Employee> employees2;
        int size = employees.size();

        employee.setId(10);
        if (employeeManager.addEmployee(employee)) {
            employees2 = employeeManager.getAllEmployees();
            assertEquals(employees.size(), employees2.size());
        }
        else assertEquals(size,employees.size());


    }
}
