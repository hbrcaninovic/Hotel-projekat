package ba.unsa.etf.rpr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class RoomDaoSQLImpl implements RoomDao {

    private Connection conn;
    private DB_konekcija k=new DB_konekcija();

    /** Constructor that establishes a connection to the database. */
    public RoomDaoSQLImpl() {
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
    public Room getById(int id) {
        try
        {
            PreparedStatement stmt=this.conn.prepareStatement("SELECT * FROM `freedb_RPR baza - projekt`.sobe WHERE broj_sobe=?");
            stmt.setInt(1,id);
            ResultSet rs= stmt.executeQuery();

            if(rs.next())
            {
                Room r=new Room();

                r.setRoom_id(rs.getInt("broj_sobe"));
                r.setRoom_type(rs.getInt("tip_sobe"));
                r.setPrice(rs.getDouble("cijena"));
                r.setVIP_services(rs.getString("VIP"));
                r.setStatus(rs.getString("status"));

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
    public Room add(Room item) {
        return null;
    }

    @Override
    public Room update(Room item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Room> getAll() {
        return null;
    }

    @Override
    public List<Room> searchByStatus(String status) {
        return null;
    }
}
