package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Guest;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GuestDaoSQLImpl extends AbstractDao<Guest> implements GuestDao {

    private static GuestDaoSQLImpl instance = null;
    private GuestDaoSQLImpl() {
        super("`freedb_RPR baza - projekt`.gosti");
    }

    public static GuestDaoSQLImpl getInstance(){
        if (instance == null) instance = new GuestDaoSQLImpl();
        return instance;
    }
    public static void removeInstance(){
        if(instance != null) instance = null;
    }



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
}
