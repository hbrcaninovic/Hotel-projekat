package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;

import java.util.List;

public interface RoomDao extends Dao<Room>{

    /**
     * Search for all available or occupied rooms.
     * @param status String which define status (busy/free) for searching rooms
     * @return List of rooms that satisfies the status condition
     * */
    List<Room> searchByStatus(String status) throws HotelExceptions;


    /** Deletes a Room from the database based on the id parameter
     * @param id int value that uniquely define room
     * */
    void deleteRoom(int id) throws HotelExceptions;

    /**
     * Gives a room which id is same as given.
     * @param id int that represents room number
     * @return Room object whose id is as same as given
     */
    Room getRoomById(int id) throws HotelExceptions;
}
