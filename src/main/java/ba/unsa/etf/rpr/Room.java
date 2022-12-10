package ba.unsa.etf.rpr;

public class Room {

    private int room_id;
    private int room_type;
    private double price;
    private String VIP_services;
    private String status;


    /**A constructor that receives attribute values as parameters and initializes them
     * @param room_id int value that represents the unique key of object
     * @param room_type int value that represents the number of beds in room
     * @param price double value for price
     * @param VIP_services String value that has 2 possible states: YES/NO
     * @param status String value that has 2 possible states: busy/free */
    public Room(int room_id, int room_type, double price, String VIP_services, String status) {
        this.room_id = room_id;
        this.room_type = room_type;
        this.price = price;
        this.VIP_services = VIP_services;
        this.status = status;
    }
}
