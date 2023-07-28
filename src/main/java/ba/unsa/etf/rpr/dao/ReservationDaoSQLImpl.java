package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;

import java.sql.ResultSet;
import java.sql.SQLException;
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
            r.setStatus(rs.getString("status"));
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
        row.put("status", object.getStatus());
        row.put("datum_dolaska", object.getDate_of_arrival());
        row.put("datum_odlaska", object.getDate_of_arrival());
        row.put("gost_id", object.getGuest_id());
        row.put("br_sobe", object.getRoom_id());

        return row;
    }

    @Override
    public List<Reservation> getByDateRange(LocalDate date1, LocalDate date2) throws HotelExceptions {
        return executeQuery("SELECT * FROM `freedb_RPR baza - projekt`.rezervacije WHERE datum_dolaska BETWEEN ? AND ?", new Object[]{date1, date2});
    }
}
