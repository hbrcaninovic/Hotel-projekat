package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.DB_konekcija;
import ba.unsa.etf.rpr.domain.Guest;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RoomDaoSQLImpl extends AbstractDao<Room> implements RoomDao {

    private static RoomDaoSQLImpl instance = null;
    private RoomDaoSQLImpl() {
        super("`freedb_RPR baza - projekt`.sobe");
    }

    public static RoomDaoSQLImpl getInstance(){
        if(instance == null) instance = new RoomDaoSQLImpl();
        return instance;
    }
    public static void removeInstance(){
        if(instance != null) instance = null;
    }

    @Override
    public Room row2object(ResultSet rs) throws HotelExceptions {
        try
        {
            Room r=new Room();
            r.setId(rs.getInt("broj_sobe"));
            r.setRoom_type(rs.getInt("tip_sobe"));
            r.setPrice(rs.getDouble("cijena"));
            r.setVIP_services(rs.getString("VIP"));
            r.setStatus(rs.getString("status"));

           // rs.close();
            return r;
        }
        catch (SQLException e){
            throw new HotelExceptions(e.getMessage(),e);
        }
    }

    @Override
    public Map<String, Object> object2row(Room object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("broj_sobe", object.getId());
        row.put("tip_sobe", object.getRoom_type());
        row.put("cijena", object.getPrice());
        row.put("VIP", object.getVIP_services());
        row.put("status", object.getStatus());

        return row;
    }


    @Override
    public List<Room> searchByStatus(String status) throws HotelExceptions {
        return executeQuery("SELECT * FROM `freedb_RPR baza - projekt`.sobe WHERE status = ?", new Object[]{status});
    }

    @Override
    public void deleteRoom(int id) throws HotelExceptions {
        String sql = "DELETE FROM `freedb_RPR baza - projekt`.sobe WHERE broj_sobe = ?";
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

    @Override
    public Room getRoomById(int id) throws HotelExceptions {
        return executeQueryUnique("SELECT * FROM `freedb_RPR baza - projekt`.sobe WHERE broj_sobe = ?", new Object[]{id});
    }


}
