package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeDaoSQLImpl implements EmployeeDao{

    private Connection conn;
    private DB_konekcija k=new DB_konekcija();

    /** Private method for finding a maximum ID in database */
    private int getMaxId(){
        int id=1;
        try {
            PreparedStatement statement = this.conn.prepareStatement("SELECT max(zaposlenik_id) FROM `freedb_RPR baza - projekt`.zaposlenici");
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                id = rs.getInt(1);
                rs.close();
                return id;
            }
        } catch (SQLException e) {
            System.out.println("Problem pri traženju maksimalnog indeksa!");
            System.out.println(e.getMessage());
        }
        return id;
    }



    /**
     * Constructor that establishes a connection to the database.
     * */
    public EmployeeDaoSQLImpl(){
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
            PreparedStatement stmt=this.conn.prepareStatement("SELECT * FROM `freedb_RPR baza - projekt`.zaposlenici WHERE zaposlenik_id=?");
            stmt.setInt(1,id);
            ResultSet rs= stmt.executeQuery();

            if(rs.next())
            {
                Employee e=new Employee();
                e.setEmployee_id(id);
                e.setUsername(rs.getString("korisnicko_ime"));
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


    // ??? -
    @Override
    public Employee add(Employee item) {


         String query="INSERT INTO `freedb_RPR baza - projekt`.zaposlenici (zaposlenik_id, korisnicko_ime, sifra, ime, prezime, adresa, email, datum_zaposlenja, posao, plata) VALUES (?,?,?,?,?,?,?,?,?,?)";


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
           // stmt.setDate(7,(java.sql.Date)Date.valueof());
            stmt.setString(8,item.getJob_title());
            stmt.setDouble(9,item.getSalary());
            stmt.setInt(10,getMaxId());

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
            System.out.println(e.getMessage());

        }

        return null;
    }

    @Override
    public Employee update(Employee item) {

        try
        {
            PreparedStatement stmt = this.conn.prepareStatement("UPDATE `freedb_RPR baza - projekt`.zaposlenici SET korisnicko_ime=?, sifra=?, ime=?, prezime=?, adresa=?, email=?, datum_zaposlenja=?, posao=?, plata=? WHERE zaposlenik_id=?");

            stmt.setString(1,item.getUsername());
            stmt.setString(2,item.getPassword());
            stmt.setString(3,item.getFirst_name());
            stmt.setString(4,item.getLast_name());
            stmt.setString(5,item.getAddress());
            stmt.setString(6,item.getEmail());
            stmt.setDate(7,(java.sql.Date)item.getHire_date());
            stmt.setString(8,item.getJob_title());
            stmt.setDouble(9,item.getSalary());
            stmt.setInt(10,item.getEmployee_id());

            stmt.executeUpdate();
            return item;
        }
        catch (SQLException e){
            System.out.println("Problem pri ažuriranju sloga u bazi podataka");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(int id) {
        try{
            PreparedStatement stmt = this.conn.prepareStatement("DELETE FROM `freedb_RPR baza - projekt`.zaposlenici WHERE zaposlenik_id=?");
            stmt.setInt(1, id);
            stmt.executeUpdate();

        }
        catch (SQLException e){
            System.out.println("Problem pri brisanju sloga u bazi podataka!");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Employee> getAll() {

        List<Employee> emp = new ArrayList<>();

        try{
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM `freedb_RPR baza - projekt`.zaposlenici");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Employee e = new Employee();

                e.setEmployee_id(rs.getInt("zaposlenik_id"));
                e.setUsername(rs.getString("korisnicko_ime"));
                e.setPassword(rs.getString("sifra"));
                e.setFirst_name(rs.getString("ime"));
                e.setLast_name(rs.getString("prezime"));
                e.setAddress(rs.getString("adresa"));
                e.setEmail(rs.getString("email"));
                e.setHire_date(rs.getDate("datum_zaposlenja"));
                e.setJob_title(rs.getString("posao"));
                e.setSalary(rs.getDouble("plata"));

                emp.add(e);
            }

            rs.close();
        }
        catch (SQLException e){
            System.out.println("Problem pri kopiranju svih slogova tebele u listu!");
            System.out.println(e.getMessage());
        }
        return emp;
    }





    // k ???
    @Override
    public List<Employee> getAfterByHireDate(Date date) {

        List<Employee> emp = new ArrayList<>();
        String s="SELECT * FROM zaposlenici WHERE datum_zaposlenja>=?";

        try
        {
            PreparedStatement stmt=this.conn.prepareStatement(s);
            stmt.setDate(1, (java.sql.Date) date);
            ResultSet rs=stmt.executeQuery();

            while (rs.next())
            {
                Employee e=new Employee();

                e.setEmployee_id(rs.getInt("zaposlenik_id"));
                e.setUsername(rs.getString("korisnicko_ime"));
                e.setPassword(rs.getString("sifra"));
                e.setFirst_name(rs.getString("ime"));
                e.setLast_name(rs.getString("prezime"));
                e.setAddress(rs.getString("adresa"));
                e.setEmail(rs.getString("email"));
                e.setHire_date(rs.getDate("datum_zaposlenja"));
                e.setJob_title(rs.getString("posao"));
                e.setSalary(rs.getDouble("plata"));

                emp.add(e);

            }
            rs.close();
        }
        catch (SQLException msg)
        {
            System.out.println("Greska prilikom pretrage slogova sa datumima prije zadatog!");
            msg.getMessage();
        }
        return emp;
    }

    // k ???
    @Override
    public List<Employee> getBeforeByHireDate(Date date) {
        List<Employee> emp = new ArrayList<>();
        String s="SELECT * FROM zaposlenici WHERE datum_zaposlenja<=?";

        try
        {
            PreparedStatement stmt=this.conn.prepareStatement(s);
            stmt.setDate(1, (java.sql.Date) date);
            ResultSet rs=stmt.executeQuery();

            while (rs.next())
            {
                Employee e=new Employee();

                e.setEmployee_id(rs.getInt("zaposlenik_id"));
                e.setUsername(rs.getString("korisnicko_ime"));
                e.setPassword(rs.getString("sifra"));
                e.setFirst_name(rs.getString("ime"));
                e.setLast_name(rs.getString("prezime"));
                e.setAddress(rs.getString("adresa"));
                e.setEmail(rs.getString("email"));
                e.setHire_date(rs.getDate("datum_zaposlenja"));
                e.setJob_title(rs.getString("posao"));
                e.setSalary(rs.getDouble("plata"));

                emp.add(e);

            }
            rs.close();
        }
        catch (SQLException msg)
        {
            System.out.println("Greska prilikom pretrage slogova sa datumima prije zadatog!");
            msg.getMessage();
        }
        return emp;
    }


    //k ???
    @Override
    public List<Employee> getByJob(String job_title) {

        List<Employee> emp = new ArrayList<>();
        String s="SELECT * FROM zaposlenici WHERE posao=?;";

        try
        {
            PreparedStatement stmt=this.conn.prepareStatement(s);
            stmt.setString(1,job_title);
            ResultSet rs=stmt.executeQuery();

            while (rs.next())
            {
                Employee e=new Employee();

                e.setEmployee_id(rs.getInt("zaposlenik_id"));
                e.setUsername(rs.getString("korisnicko_ime"));
                e.setPassword(rs.getString("sifra"));
                e.setFirst_name(rs.getString("ime"));
                e.setLast_name(rs.getString("prezime"));
                e.setAddress(rs.getString("adresa"));
                e.setEmail(rs.getString("email"));
                e.setHire_date(rs.getDate("datum_zaposlenja"));
                e.setJob_title(rs.getString("posao"));
                e.setSalary(rs.getDouble("plata"));

                emp.add(e);

            }
            rs.close();
        }
        catch (SQLException msg)
        {
            System.out.println("Greska prilikom pretrage slogova sa nazivom posla!");
            msg.getMessage();
        }
        return emp;
    }
}
