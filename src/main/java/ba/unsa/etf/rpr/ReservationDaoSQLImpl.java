package ba.unsa.etf.rpr;

import java.sql.*;
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

    @Override
    public Reservation add(Reservation item) {
        return null;
    }

    @Override
    public Reservation update(Reservation item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Reservation> getAll() {
        return null;
    }

    @Override
    public List<Reservation> getByDateRange(Date date1, Date date2) {
        return null;
    }
}
