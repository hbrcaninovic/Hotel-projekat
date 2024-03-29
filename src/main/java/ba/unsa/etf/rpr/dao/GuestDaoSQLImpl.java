package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Guest;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GuestDaoSQLImpl extends AbstractDao<Guest> implements GuestDao {

    private static GuestDaoSQLImpl instance = null;

    /** Private constructor that is a part of the Singleton design pattern*/
    private GuestDaoSQLImpl() {
        super("`freedb_RPR baza - projekt`.gosti");
    }

    /** Static method that retrieves a singleton instance */
    public static GuestDaoSQLImpl getInstance(){
        if (instance == null) instance = new GuestDaoSQLImpl();
        return instance;
    }

    /** Static method that removes a singleton instance*/
    public static void removeInstance(){
        if(instance != null) instance = null;
    }



    /** A method that converts a database row into an object */
    @Override
    public Guest row2object(ResultSet rs) throws HotelExceptions {
        try
        {
            Guest g = new Guest();
            g.setId(rs.getInt("gost_id"));
            g.setFirst_name(rs.getString("ime"));
            g.setLast_name(rs.getString("prezime"));
            g.setEmail(rs.getString("email"));
            g.setContact_number(rs.getString("kontakt_broj"));
            //rs.close();
            return g;
        }
        catch (SQLException e){
            throw new HotelExceptions(e.getMessage(),e);
        }
    }

    /** A method that converts an object into a database row */
    @Override
    public Map<String, Object> object2row(Guest object) {

        Map<String, Object> row = new TreeMap<>();
        row.put("gost_id", object.getId());
        row.put("ime", object.getFirst_name());
        row.put("prezime", object.getLast_name());
        row.put("email", object.getEmail());
        row.put("kontakt_broj", object.getContact_number());
        return row;
    }


    @Override
    public List<Guest> getByDateRange(LocalDate date1, LocalDate date2) {
        return null;
    }


    /**
     * Gives a guest which id is same as given.
     * @param id int that represents guest id
     * @return Guest objects whose id is as same as given
     */
    @Override
    public Guest getGuestById(int id) throws HotelExceptions {
        return executeQueryUnique("SELECT * FROM `freedb_RPR baza - projekt`.gosti WHERE gost_id = ?", new Object[]{id});

    }

    /** Deletes a Guest from the database based on the id parameter
     * @param id int value that uniquely define Guest
     * */
    @Override
    public void deleteGuest(int id) throws HotelExceptions {
        String sql = "DELETE FROM `freedb_RPR baza - projekt`.gosti WHERE gost_id = ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new HotelExceptions(e.getMessage(), e);
        }
    }
}
