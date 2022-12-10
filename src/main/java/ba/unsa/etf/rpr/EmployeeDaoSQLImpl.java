package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class EmployeeDaoSQLImpl implements EmployeeDao{

    private Connection conn;
    private DB_konekcija k=new DB_konekcija();


    /**
     * Constructor that establishes a connection to the database.
     * */
    public EmployeeDaoSQLImpl()
    {
        try
        {
            this.conn=DriverManager.getConnection(k.getUrl(),k.getUser(), k.getPassword());
        }
        catch (SQLException e)
        {
            System.out.println("Greška pri uspostavljanju konekcije sa bazom podataka!");
            System.out.println(e.getMessage());
        }
    }


    @Override
    public Employee getById(int id) {
        try
        {
            PreparedStatement stmt=this.conn.prepareStatement("SELECT * FROM zaposlenici WHERE zaposlenik_id=?");
            stmt.setInt(1,id);
            ResultSet rs= stmt.executeQuery();

            if(rs.next())
            {
                Employee e=new Employee();
                e.setEmployee_id(id);
                e.setUsername(rs.getString("korisnicko_ime_id"));
                e.setPassword(rs.getString("sifra"));
                e.setFirst_name(rs.getString("ime"));
                e.setLast_name(rs.getString("prezime"));
                e.setAddress(rs.getString("adresa"));
                e.setEmail(rs.getString("email"));
                e.setHire_date(rs.getDate("datum_zaposlenja"));
                e.setJob_title(rs.getString("posao"));
                e.setSalary(rs.getDouble("plata"));
                rs.close();
                return e;
            }
            else
            {
                return null;
            }

        }
        catch(SQLException e)
        {
            System.out.println("Greska prilikom izvrsavanja upita za pristup elementu preko id-a!");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Employee add(Employee item) {

       /* String query="INSERT INTO zaposlenici (korisnicko_ime_id, sifra, ime, prezime, adresa, email, datum_zaposlenja, posao, plata) VALUES (?,?,?,?,?,?,?,?,?)";
        try
        {
            PreparedStatement stmt=this.conn.prepareStatement(query);
            stmt.setString(1,item.getUsername());
            stmt.setString(2,item.getPassword());
            stmt.setString(3,item.getFirst_name());
            stmt.setString(4,item.getLast_name());
            stmt.setString(5, item.getAddress());
            stmt.setString(6,item.getEmail());
            stmt.setDate(7, (java.sql.Date) item.getHire_date());
            stmt.setString(8,item.getJob_title());
            stmt.setDouble(9,item.getSalary());

            stmt.executeUpdate();

            //OB!!!
           // ResultSet rs = stmt.getGeneratedKeys();
           // rs.next(); // we know that there is one key
            //item.setId(rs.getInt(1)); //set id to return it back

            return item;
        }
        catch(SQLException e)
        {
            System.out.println("Greska pri dodavanju novog sloga u tabelu zaposlenika!");
            System.out.println();

        }

        */
        return null;
    }

    @Override
    public Employee update(Employee item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Employee> getAll() {
        return null;
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
}
