package ba.unsa.etf.rpr;

import java.util.List;

public interface RoomDao extends Dao<Room>{

    /**
     * Search for all available or occupied rooms.
     * @param status String which define status (busy/free) for searching rooms
     * @return List of rooms that satisfies the status condition
     * */
    List<Room> searchByStatus(String status);

}
