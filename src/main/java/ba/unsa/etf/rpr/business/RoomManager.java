package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;

import java.util.List;

public class RoomManager {

    /** A method that returns a list of all rooms from the database
     * @return List of Rooms
     * */
    public List<Room> getAllRooms() throws HotelExceptions {
        return DaoFactory.roomDao().getAll();
    }

    /** Gives information about the room from the database based on the room number
     * @param roomNumber int value that represents number of room
     * @return Room value that number is same as given
     * */
    public Room getRoom(int roomNumber) throws HotelExceptions {
        return DaoFactory.roomDao().getRoomById(roomNumber);
    }

    /** Adds a room to the database
     * @param room Room object that represent room
     * @return boolean value which gives confirmation of the successful addition of the room to the database
     * */
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

    /** Deletes the specified room from the database
     * @param room Room object that deletes
     * @return boolean value which gives confirmation of the successful deletion of the room to the database
     * */
    public boolean deleteRoom(Room room){
        try {
            DaoFactory.roomDao().deleteRoom(room.getId());
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /** Updates the specified room from the database
     * @param room Room object that updates
     * @return boolean value which gives confirmation of the successful update of the room to the database
     * */
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
