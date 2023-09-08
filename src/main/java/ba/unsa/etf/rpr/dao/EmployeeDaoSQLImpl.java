package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EmployeeDaoSQLImpl extends AbstractDao<Employee> implements EmployeeDao{

    private static EmployeeDaoSQLImpl instance = null;

    /** Private constructor that is a part of the Singleton design pattern*/
    private EmployeeDaoSQLImpl() {
        super("`freedb_RPR baza - projekt`.zaposlenici");
    }

    /** Static method that retrieves a singleton instance */
    public static EmployeeDaoSQLImpl getInstance(){
        if(instance == null) instance = new EmployeeDaoSQLImpl();
        return instance;
    }

    /** Static method that removes a singleton instance*/
    public static void removeInstance(){
        if(instance != null) instance = null;
    }

    /** A method that converts a database row into an object */
    @Override
    public Employee row2object(ResultSet rs) throws HotelExceptions {
        try
        {
            Employee e = new Employee();
            e.setId(rs.getInt("zaposlenik_id"));
            e.setUsername(rs.getString("korisnicko_ime"));
            e.setPassword(rs.getString("sifra"));
            e.setFirst_name(rs.getString("ime"));
            e.setLast_name(rs.getString("prezime"));
            e.setEmail(rs.getString("email"));
            e.setJob_title(rs.getString("posao"));
            e.setSalary(rs.getDouble("plata"));
            e.setAdmin(rs.getInt("admin"));
            //rs.close();
            return e;
        }
        catch (SQLException e){
            throw new HotelExceptions(e.getMessage(),e);
        }
    }

    /** A method that converts an object into a database row */
    @Override
    public Map<String, Object> object2row(Employee object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("zaposlenik_id", object.getId());
        row.put("korisnicko_ime", object.getUsername());
        row.put("sifra", object.getPassword());
        row.put("ime", object.getFirst_name());
        row.put("prezime", object.getLast_name());
        row.put("email", object.getEmail());
        row.put("posao", object.getJob_title());
        row.put("plata", object.getSalary());
        row.put("admin",object.getAdmin());
        return row;
    }



    @Override
    public List<Employee> getAfterByHireDate(Date date) {
        return null;
    }

    @Override
    public List<Employee> getBeforeByHireDate(Date date) {
        return null;
    }

    @Override
    public List<Employee> getByJob(String job_title) {
        return null;
    }


    /**
     * Gives an employee which username is same as given.
     * @param username String that represents username
     * @return Employee objects whose username is as same as given
     */
    @Override
    public Employee getByUsername(String username) throws HotelExceptions {
        return executeQueryUnique("SELECT * FROM `freedb_RPR baza - projekt`.zaposlenici WHERE korisnicko_ime=?",new Object[]{username});
    }

    /**
     * Gives an employee which username and password are same as given.
     * @param username String that represents username
     * @param password String that represents password
     * @return Employee objects whose username and password are as same as given
     */
    @Override
    public Employee getEmployeeByUsernameAndPassword(String username, String password) {
        Employee employee = new Employee();
        try {
            return executeQueryUnique("SELECT * FROM `freedb_RPR baza - projekt`.zaposlenici WHERE korisnicko_ime = ? AND sifra = ?", new Object[]{username, password});
        }
        catch (HotelExceptions e){
            return employee;
        }
    }

    /** Deletes an Employee from the database based on the id parameter
     * @param id int value that uniquely define Employee
     * */
    @Override
    public void deleteEmployee(int id) throws HotelExceptions {
        String sql = "DELETE FROM `freedb_RPR baza - projekt`.zaposlenici WHERE zaposlenik_id = ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new HotelExceptions(e.getMessage(), e);
        }

    }
}
