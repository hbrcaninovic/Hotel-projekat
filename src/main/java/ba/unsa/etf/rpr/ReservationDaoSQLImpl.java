package ba.unsa.etf.rpr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
