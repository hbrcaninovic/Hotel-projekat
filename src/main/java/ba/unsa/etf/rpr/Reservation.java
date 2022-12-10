package ba.unsa.etf.rpr;

import java.util.Date;

public class Reservation {

    private int reservation_id;
    private String status;
    private Date date_of_arrival;
    private Date departure_date;
    private int guest_id;
    private int room_id;

    /**A constructor that receives attribute values as parameters and initializes them
     * @param reservation_id int value that represents the unique key of object
     * @param status String value that has 2 possible states: confirmed/unconfirmed
     * @param date_of_arrival Date value that represents date_of_arrival
     * @param departure_date Date value that represents departure_date
     * @param guest_id int value that represents guest_id
     * @param room_id int value that represents room_id
     * */
    public Reservation(int reservation_id, String status, Date date_of_arrival, Date departure_date, int guest_id, int room_id) {
        this.reservation_id = reservation_id;
        this.status = status;
        this.date_of_arrival = date_of_arrival;
        this.departure_date = departure_date;
        this.guest_id = guest_id;
        this.room_id = room_id;
    }


}
