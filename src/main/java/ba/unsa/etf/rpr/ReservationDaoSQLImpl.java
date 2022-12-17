package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationDaoSQLImpl implements ReservationDao{

    private Connection conn;
    private DB_konekcija k=new DB_konekcija();

    /** Constructor that establishes a connection to the database. */
    public ReservationDaoSQLImpl()
    {
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
    public Reservation getById(int id) {
        try
        {
            PreparedStatement stmt=this.conn.prepareStatement("SELECT * FROM `freedb_RPR baza - projekt`.rezervacije WHERE broj_rezervacije=?");
            stmt.setInt(1,id);
            ResultSet rs= stmt.executeQuery();

            if(rs.next())
            {
                Reservation r=new Reservation();

                r.setReservation_id(rs.getInt("broj_rezervacije"));
                r.setStatus(rs.getString("status"));
                r.setDate_of_arrival(rs.getDate("datum_dolaska"));
                r.setDeparture_date(rs.getDate("datum_odlaska"));
                r.setGuest_id(rs.getInt("gost_id"));
                r.setRoom_id(rs.getInt("br_sobe"));

                rs.close();
                return r;
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
    public Reservation add(Reservation item) {
        try
        {
            PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO `freedb_RPR baza - projekt`.rezervacije (broj_rezervacije, status, datum_dolaska, datum_odlaska, gost_id, br_sobe) VALUES (?,?,?,?,?,?)");

            stmt.setInt(1,item.getReservation_id());
            stmt.setString(2,item.getStatus());
            stmt.setDate(3, (java.sql.Date) item.getDate_of_arrival());
            stmt.setDate(4, (java.sql.Date) item.getDeparture_date());
            stmt.setInt(5,item.getGuest_id());
            stmt.setInt(6,item.getRoom_id());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next())
            {
                Reservation r=new Reservation();
                r.setReservation_id(rs.getInt("broj_rezervacije"));
                r.setStatus(rs.getString("status"));
                r.setDate_of_arrival(rs.getDate("datum_dolaska"));
                r.setDeparture_date(rs.getDate("datum_odlaska"));
                r.setGuest_id(rs.getInt("gost_id"));
                r.setRoom_id(rs.getInt("br_sobe"));

                rs.next(); // we know that there is one key
                item.setReservation_id(rs.getInt(1)); //set id to return it back
                return item;
            }
            else
            {
                return null;
            }
        }
        catch (SQLException e)
        {
            System.out.println("Greska pri dodavanju novog sloga u bazu!");
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Reservation update(Reservation item) {
        try
        {
            PreparedStatement stmt = this.conn.prepareStatement("UPDATE `freedb_RPR baza - projekt`.rezervacije SET status = ?, datum_dolaska = ?, datum_odlaska = ?, gost_id = ?, br_sobe = ? WHERE (broj_rezervacije = ?)");

            stmt.setString(1,item.getStatus());
            stmt.setDate(2, (java.sql.Date) item.getDate_of_arrival());
            stmt.setDate(3, (java.sql.Date) item.getDeparture_date());
            stmt.setInt(4,item.getGuest_id());
            stmt.setInt(5,item.getRoom_id());
            stmt.setInt(6,item.getReservation_id());

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
            PreparedStatement stmt =this.conn.prepareStatement("DELETE FROM `freedb_RPR baza - projekt`.rezervacije WHERE broj_rezervacije=?");
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
    public List<Reservation> getAll() {

        List<Reservation> reservations = new ArrayList<>();

        try
        {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM `freedb_RPR baza - projekt`.rezervacije");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Reservation r = new Reservation();

                r.setReservation_id(rs.getInt("broj_rezervacije"));
                r.setStatus(rs.getString("status"));
                r.setDate_of_arrival(rs.getDate("datum_dolaska"));
                r.setDeparture_date(rs.getDate("datum_odlaska"));
                r.setGuest_id(rs.getInt("gost_id"));
                r.setRoom_id(rs.getInt("br_sobe"));

                reservations.add(r);
            }

            rs.close();
        }
        catch (SQLException e) {
            System.out.println("Problem pri kopiranju svih slogova tebele u listu!");
            System.out.println(e.getMessage());
        }
        return reservations;
    }

    @Override
    public List<Reservation> getByDateRange(Date date1, Date date2) {
        return null;
    }
}
