package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ReservationDaoSQLImpl extends AbstractDao<Reservation> implements ReservationDao{


    private static ReservationDaoSQLImpl instance = null;
    private ReservationDaoSQLImpl(){
        super("`freedb_RPR baza - projekt`.rezervacije");
    }

    public static ReservationDaoSQLImpl getInstance(){
        if(instance == null) instance = new ReservationDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if(instance != null) instance = null;
    }

    @Override
    public Reservation row2object(ResultSet rs) throws HotelExceptions {
        try
        {
            Reservation r=new Reservation();
            r.setId(rs.getInt("broj_rezervacije"));
            r.setDate_of_arrival(rs.getDate("datum_dolaska").toLocalDate());
            r.setDeparture_date(rs.getDate("datum_odlaska").toLocalDate());
            r.setGuest_id(rs.getInt("gost_id"));
            r.setRoom_id(rs.getInt("br_sobe"));

            //rs.close();
            return r;
        }
        catch (SQLException e){
            throw new HotelExceptions(e.getMessage(),e);
        }
    }

    @Override
    public Map<String, Object> object2row(Reservation object) {

        Map<String, Object> row = new TreeMap<>();
        row.put("broj_rezervacije", object.getId());
        row.put("datum_dolaska", object.getDate_of_arrival());
        row.put("datum_odlaska", object.getDeparture_date());
        row.put("gost_id", object.getGuest_id());
        row.put("br_sobe", object.getRoom_id());

        return row;
    }

    @Override
    public List<Reservation> getByDateRange(LocalDate date1, LocalDate date2) throws HotelExceptions {
        return executeQuery("SELECT * FROM `freedb_RPR baza - projekt`.rezervacije WHERE datum_dolaska BETWEEN ? AND ?", new Object[]{date1, date2});
    }

    @Override
    public Integer getMaxReservationId() throws HotelExceptions {
        String sql = "SELECT max(broj_rezervacije) as id FROM `freedb_RPR baza - projekt`.rezervacije";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public void deleteReservation(int id) throws HotelExceptions {
        String sql = "DELETE FROM `freedb_RPR baza - projekt`.rezervacije WHERE broj_rezervacije = ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
            throw new HotelExceptions(e.getMessage(), e);
        }
    }
}
