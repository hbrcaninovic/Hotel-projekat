package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.EmployeeDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeDaoSQLImplTest {

    private Employee employee;
    private EmployeeDaoSQLImpl employeeDaoSQLMock;
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
         employeeDaoSQLMock = Mockito.mock(EmployeeDaoSQLImpl.class);
    }

    @Test
    public void testGetEmployeeByUsernameAndPassword() {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);

        daoFactoryMockedStatic.when(DaoFactory::employeeDao).thenReturn(employeeDaoSQLMock);
        Mockito.when(DaoFactory.employeeDao().getEmployeeByUsernameAndPassword(employee.getUsername(),employee.getPassword())).thenReturn(employee);

        Employee testEmployee = employeeDaoSQLMock.getEmployeeByUsernameAndPassword("username","password");
        assertAll("Test",
                () -> assertEquals(testEmployee.getUsername(),employee.getUsername()),
                () -> assertEquals(testEmployee.getPassword(),employee.getPassword()));
        daoFactoryMockedStatic.close();
    }

    @Test
    public void testGetByUsername() throws HotelExceptions {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);

        daoFactoryMockedStatic.when(DaoFactory::employeeDao).thenReturn(employeeDaoSQLMock);
        Mockito.when(DaoFactory.employeeDao().getByUsername(employee.getUsername())).thenReturn(employee);

        Employee testEmployee = employeeDaoSQLMock.getByUsername("username");
        assertEquals(testEmployee.getUsername(),employee.getUsername());
    }


}
