package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;

import java.util.List;

public class RoomManager {

    public List<Room> getAllRooms() throws HotelExceptions {
        return DaoFactory.roomDao().getAll();
    }

}
