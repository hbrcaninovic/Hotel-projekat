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

    //??? Provjeriti
    @Override
    public Room add(Room item) {

        try
        {
            PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO `freedb_RPR baza - projekt`.sobe (broj_sobe, tip_sobe, cijena, VIP, status) VALUES (?, ?, ?, ?, ?)");

            stmt.setInt(1,item.getRoom_id());
            stmt.setInt(2,item.getRoom_type());
            stmt.setDouble(3,item.getPrice());
            stmt.setString(4,item.getVIP_services());
            stmt.setString(5, item.getStatus());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next())
            {
                Room r=new Room();
                r.setRoom_id(rs.getInt("broj_sobe"));
                r.setRoom_type(rs.getInt("tip_sobe"));
                r.setPrice(rs.getDouble("cijena"));
                r.setVIP_services(rs.getString("VIP"));
                r.setStatus(rs.getString("status"));

                rs.next(); // we know that there is one key
                item.setRoom_id(rs.getInt(1)); //set id to return it back
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
    public Room update(Room item) {
        try
        {
            PreparedStatement stmt = this.conn.prepareStatement("UPDATE `freedb_RPR baza - projekt`.sobe SET tip_sobe = ?, cijena = ?, VIP = ?, status = ? WHERE (broj_sobe = ?)");

            stmt.setInt(1,item.getRoom_type());
            stmt.setDouble(2,item.getPrice());
            stmt.setString(3,item.getVIP_services());
            stmt.setString(4,item.getStatus());
            stmt.setInt(5,item.getRoom_id());

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
            PreparedStatement stmt =this.conn.prepareStatement("DELETE FROM `freedb_RPR baza - projekt`.sobe WHERE broj_sobe=?");
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
    public List<Room> getAll() {
        return null;
    }

    @Override
    public List<Room> searchByStatus(String status) {
        return null;
    }
}
