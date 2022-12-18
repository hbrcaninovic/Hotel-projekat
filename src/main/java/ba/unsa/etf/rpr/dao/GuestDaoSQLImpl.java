package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.DB_konekcija;
import ba.unsa.etf.rpr.Guest;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuestDaoSQLImpl implements GuestDao {

    private Connection conn;
    private DB_konekcija k=new DB_konekcija();


    /** Private method for finding a maximum _id in database */
    private int getMaxId(){
        int id=1;
        try {
            PreparedStatement statement = this.conn.prepareStatement("SELECT max(gost_id) FROM `freedb_RPR baza - projekt`.gosti");
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


    /** Constructor that establishes a connection to the database. */
    public GuestDaoSQLImpl(){
        try
        {
            this.conn= DriverManager.getConnection(k.getUrl(),k.getUser(), k.getPassword());
        }
        catch (SQLException e)
        {
            System.out.println("Greška pri uspostavljanju konekcije sa bazom podataka!");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Guest getById(int id) {
        try
        {
            PreparedStatement stmt=this.conn.prepareStatement("SELECT * FROM `freedb_RPR baza - projekt`.gosti WHERE gost_id=?");
            stmt.setInt(1,id);
            ResultSet rs= stmt.executeQuery();

            if(rs.next())
            {
                Guest g=new Guest();
                g.setID(rs.getInt("gost_id"));
                g.setFirst_name(rs.getString("ime"));
                g.setLast_name(rs.getString("prezime"));
                g.setAddress(rs.getString("adresa"));
                g.setEmail(rs.getString("email"));
                g.setContact_number(rs.getString("kontakt_broj"));

                rs.close();
                return g;
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

    //???
    @Override
    public Guest add(Guest item) {

        try
        {
            PreparedStatement stmt=this.conn.prepareStatement("INSERT INTO `freedb_RPR baza - projekt`.gosti (gost_id, ime, prezime, adresa, email, kontakt_broj) VALUES (?,?,?,?,?,?)");
            stmt.setInt(1,item.getID());
            stmt.setString(2,item.getFirst_name());
            stmt.setString(3,item.getLast_name());
            stmt.setString(4,item.getAddress());
            stmt.setString(5,item.getEmail());
            stmt.setString(6,item.getContact_number());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            // 98-111
            if(rs.next())
            {
                Guest g=new Guest();
                g.setID(rs.getInt("gost_id"));
                g.setFirst_name(rs.getString("ime"));
                g.setLast_name(rs.getString("prezime"));
                g.setAddress(rs.getString("adresa"));
                g.setEmail(rs.getString("email"));
                g.setContact_number(rs.getString("kontakt_broj"));

                rs.next(); // we know that there is one key
                item.setID(rs.getInt(1)); //set id to return it back
                return item;
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
    public Guest update(Guest item) {
        try
        {
            PreparedStatement stmt = this.conn.prepareStatement("UPDATE `freedb_RPR baza - projekt`.gosti SET ime = ?, prezime=?, adresa = ?, email = ?, kontakt_broj =? WHERE gost_id = ?");

            stmt.setString(1,item.getFirst_name());
            stmt.setString(2,item.getLast_name());
            stmt.setString(3,item.getAddress());
            stmt.setString(4,item.getEmail());
            stmt.setString(5,item.getContact_number());
            stmt.setInt(6,item.getID());

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

        try
        {
            PreparedStatement stmt =this.conn.prepareStatement("DELETE FROM `freedb_RPR baza - projekt`.gosti WHERE gost_id=?");
            stmt.setInt(1,id);
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("Greška prilikom brisanja sloga!");
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Guest> getAll() {

        List<Guest> guests = new ArrayList<>();

        try
        {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM `freedb_RPR baza - projekt`.gosti");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Guest g = new Guest();

                g.setID(rs.getInt("gost_id"));
                g.setFirst_name(rs.getString("ime"));
                g.setLast_name(rs.getString("prezime"));
                g.setAddress(rs.getString("adresa"));
                g.setEmail(rs.getString("email"));
                g.setContact_number(rs.getString("kontakt_broj"));
                guests.add(g);
            }

            rs.close();
        }
        catch (SQLException e) {
            System.out.println("Problem pri kopiranju svih slogova tebele u listu!");
            System.out.println(e.getMessage());
        }
        return guests;
    }

    //???
    @Override
    public List<Guest> getByDateRange(Date date1, Date date2) {
        List<Guest> guests = new ArrayList<>();

        try
        {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM `freedb_RPR baza - projekt`.gosti g, `freedb_RPR baza - projekt`.rezervacije r WHERE  g.gost_id=r.gost_id AND  r.datum_dolaska>=? AND  r.datum_odlaska<=?");
            stmt.setDate(1, new java.sql.Date (date1.getTime()));
            stmt.setDate(2, new java.sql.Date (date2.getTime()));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Guest g = new Guest();

                g.setID(rs.getInt("gost_id"));
                g.setFirst_name(rs.getString("ime"));
                g.setLast_name(rs.getString("prezime"));
                g.setAddress(rs.getString("adresa"));
                g.setEmail(rs.getString("email"));
                g.setContact_number(rs.getString("kontakt_broj"));
                guests.add(g);
            }

            rs.close();
        }
        catch (SQLException e) {
            System.out.println("Problem pri kopiranju svih slogova tebele u listu!");
            System.out.println(e.getMessage());
        }
        return guests;
    }
}
