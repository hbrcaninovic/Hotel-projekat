package ba.unsa.etf.rpr.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Reservation implements Idable {

    private int reservation_id;
    private String status;
    private LocalDate date_of_arrival;
    private LocalDate departure_date;
    private int guest_id;
    private int room_id;

    /**A constructor that receives attribute values as parameters and initializes them
     * @param reservation_id int value that represents the unique key of object
     * @param status String value that has 2 possible states: confirmed/unconfirmed
     * @param date_of_arrival LocalDate value that represents date_of_arrival
     * @param departure_date Date value that represents departure_date
     * @param guest_id int value that represents guest_id
     * @param room_id int value that represents room_id
     * */
    public Reservation(int reservation_id, String status, LocalDate date_of_arrival, LocalDate departure_date, int guest_id, int room_id) {
        this.reservation_id = reservation_id;
        this.status = status;
        this.date_of_arrival = date_of_arrival;
        this.departure_date = departure_date;
        this.guest_id = guest_id;
        this.room_id = room_id;
    }

    public Reservation() {

    }

    /**
     * Getter method for reservation_id attribute
     * @return int value that represents reservation_id
     * */
    public int getReservation_id() {
        return reservation_id;
    }

    /**Setter method for reservation_id attribute */
    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    /**
     * Getter method for status attribute
     * @return String value that represents status
     * */
    public String getStatus() {
        return status;
    }

    /**Setter method for status attribute */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getter method for date_of_arrival attribute
     * @return Date value that represents date_of_arrival
     * */
    public LocalDate getDate_of_arrival() {
        return date_of_arrival;
    }

    /**Setter method for date_of_arrival attribute */
    public void setDate_of_arrival(LocalDate date_of_arrival) {
        this.date_of_arrival = date_of_arrival;
    }

    /**
     * Getter method for departure_date attribute
     * @return Date value that represents departure_date
     * */
    public LocalDate getDeparture_date() {
        return departure_date;
    }

    /**Setter method for departure_date attribute */
    public void setDeparture_date(LocalDate departure_date) {
        this.departure_date = departure_date;
    }

    /**
     * Getter method for guest_id attribute
     * @return int value that represents guest_id
     * */
    public int getGuest_id() {
        return guest_id;
    }

    /**Setter method for guest_id attribute */
    public void setGuest_id(int guest_id) {
        this.guest_id = guest_id;
    }

    /**
     * Getter method for room_id attribute
     * @return int value that represents room_id
     * */
    public int getRoom_id() {
        return room_id;
    }

    /**Setter method for room_id attribute */
    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }




    /**This method helps to compare two objects
     * @return boolean value true - if two objects are equal, otherwise  returns false */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return reservation_id == that.reservation_id && guest_id == that.guest_id && room_id == that.room_id && status.equals(that.status) && date_of_arrival.equals(that.date_of_arrival) && departure_date.equals(that.departure_date);
    }

    /**Create and return hash code of object
     * @return int value that represents hash code*/
    @Override
    public int hashCode() {
        return Objects.hash(reservation_id, status, date_of_arrival, departure_date, guest_id, room_id);
    }

    /**Generate String that represents object suitable for printing and other usages
     * @return String value created of attributes of an object
     * */
    @Override
    public String toString() {
        return "Reservation{" +
                "reservation_id=" + reservation_id +
                ", status='" + status + '\'' +
                ", date_of_arrival=" + date_of_arrival +
                ", departure_date=" + departure_date +
                ", guest_id=" + guest_id +
                ", room_id=" + room_id +
                '}';
    }




    /**
     * Implementation of setID method from Idable
     * */
    @Override
    public void setId(int i) {
        setReservation_id(i);
    }
    /**
     * Implementation of getID method from Idable
     * */
    @Override
    public int getId() {
        return getReservation_id();
    }
}