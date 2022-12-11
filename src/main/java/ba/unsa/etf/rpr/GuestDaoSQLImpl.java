package ba.unsa.etf.rpr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class GuestDaoSQLImpl implements GuestDao {

    private Connection conn;
    private DB_konekcija k=new DB_konekcija();


    /** Private method for finding a maximum _id in database */
    private int getMaxId(){
        int id=1;
        try {
            PreparedStatement statement = this.conn.prepareStatement("SELECT MAX(zaposlenik_id)+1 FROM zaposlenici");
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







    @Override
    public Guest getById(int id) {
        return null;
    }

    @Override
    public Guest add(Guest item) {
        return null;
    }

    @Override
    public Guest update(Guest item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Guest> getAll() {
        return null;
    }

    @Override
    public List<Guest> getByDateRange(Date date1, Date date2) {
        return null;
    }
}
