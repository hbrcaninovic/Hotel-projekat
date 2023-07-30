package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;

import java.util.List;

public class RoomManager {

    public List<Room> getAllRooms() throws HotelExceptions {
        return DaoFactory.roomDao().getAll();
    }
    public Room getRoom(int roomNumber) throws HotelExceptions {
        return DaoFactory.roomDao().getRoomById(roomNumber);
    }
    public boolean addRoom(Room room) throws HotelExceptions {
        try {
            DaoFactory.roomDao().add(room);
            return true;
        }
        catch (Exception m)
        {
            return true;
        }
    }

    public boolean deleteRoom(Room room){
        try {
            DaoFactory.roomDao().deleteRoom(room.getId());
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean updateRoom(Room room){
        try {
            DaoFactory.roomDao().update(room);
            return true;
        }
        catch (HotelExceptions e) {
            return false;
        }
    }

}
