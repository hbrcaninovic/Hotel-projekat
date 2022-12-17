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
