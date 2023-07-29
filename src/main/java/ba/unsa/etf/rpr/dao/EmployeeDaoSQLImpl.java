package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EmployeeDaoSQLImpl extends AbstractDao<Employee> implements EmployeeDao{

    private static EmployeeDaoSQLImpl instance = null;
    private EmployeeDaoSQLImpl() {
        super("`freedb_RPR baza - projekt`.zaposlenici");
    }

    public static EmployeeDaoSQLImpl getInstance(){
        if(instance == null) instance = new EmployeeDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance != null) instance = null;
    }

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
            e.setAddress(rs.getString("adresa"));
            e.setEmail(rs.getString("email"));
            e.setHire_date(rs.getDate("datum_zaposlenja").toLocalDate());
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

    @Override
    public Map<String, Object> object2row(Employee object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("zaposlenik_id", object.getId());
        row.put("korisnicko_ime", object.getUsername());
        row.put("sifra", object.getPassword());
        row.put("ime", object.getFirst_name());
        row.put("prezime", object.getLast_name());
        row.put("adresa", object.getAddress());
        row.put("email", object.getEmail());
        row.put("datum_zaposlenja", object.getHire_date());
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


    @Override
    public Employee getByUsername(String username) throws HotelExceptions {
        return executeQueryUnique("SELECT * FROM `freedb_RPR baza - projekt`.zaposlenici WHERE zaposlenik_id=?",new Object[]{username});
    }

    @Override
    public Employee getEmployeeByUsernameAndPassword(String username, String password) {
        Employee employee = new Employee();
        try {
            return executeQueryUnique("SELECT * FROM `freedb_RPR baza - projekt`.zaposlenici WHERE korisnicko_ime LIKE ? AND sifra LIKE ?", new Object[]{username, password});
        }
        catch (HotelExceptions e){
            return employee;
        }
    }
}
