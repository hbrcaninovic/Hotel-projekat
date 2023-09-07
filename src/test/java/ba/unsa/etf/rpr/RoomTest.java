package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.domain.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {
    private Room room;

    @Test
    public void testBasicMethods() {
        room = new Room(1,1,1.1,"NE","slobodna");
        assertEquals(1,room.getId());

        room.setId(10);
        assertEquals(10,room.getId());

        Room room2 = new Room(room.getId(),room.getRoom_type(),room.getPrice(),room.getVIP_services(),room.getStatus());
        assertEquals(room.toString(),room2.toString());
    }
}
