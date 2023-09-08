package ba.unsa.etf.rpr.domain;

import java.time.LocalDate;

public class ReservationAndGuest {

    private Reservation reservation;
    private Guest guest;

    private LocalDate date_of_arrival;
    private LocalDate departure_date;
    private int guest_id;
    private int room_id;
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String contact_number;

    /**A constructor that receives attribute values as parameters and initializes them
     * @param reservation  value that represents the Reservation object
     * @param guest        value that represents the Guest object
     * */
    public ReservationAndGuest(Reservation reservation, Guest guest) {
        this.reservation = reservation;
        this.guest = guest;
        setDate_of_arrival(reservation.getDate_of_arrival());
        setDeparture_date(reservation.getDeparture_date());
        setGuest_id(reservation.getGuest_id());
        setRoom_id(reservation.getRoom_id());
        setId(guest.getId());
        setFirst_name(guest.getFirst_name());
        setLast_name(guest.getLast_name());
        setEmail(guest.getEmail());
        setContact_number(guest.getContact_number());
    }

    /** Implicit constructor
     */
    public ReservationAndGuest(){

    }

    /**
     * Getter method for reservation attribute
     * @return value that represents the Reservation object
     * */
    public Reservation getReservation() {
        return reservation;
    }

    /**Setter method for guest attribute
     * @param reservation value that represents the Reservation object
     * */
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    /**
     * Getter method for reservation attribute
     * @return value that represents the Guest object
     * */
    public Guest getGuest() {
        return guest;
    }

    /**Setter method for guest attribute
     * @param guest value that represents the Guest object
     * */
    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    /**
     * Getter method for date_of_arrival attribute
     * @return Date value that represents date_of_arrival
     * */
    public LocalDate getDate_of_arrival() {
        return date_of_arrival;
    }

    /**Setter method for date_of_arrival attribute
     * @param date_of_arrival LocalDate value that represents date_of_arrival
     * */
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

    /**Setter method for departure_date attribute
     * @param departure_date Date value that represents departure_date
     * */
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

    /**Setter method for guest_id attribute
     * @param guest_id int value that represents the unique key of object
     * */
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

    /**Setter method for room_id attribute
     * @param room_id int value that represents the unique key of object
     * */
    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    /**
     * Getter method for room_id attribute
     * @return int value that represents room_ID
     * */
    public int getId() {
        return id;
    }

    /**Setter method for room_id attribute
     * @param id int value that represents the unique key of object
     * */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter method for first_name attribute
     * @return String value that represents first_name
     * */
    public String getFirst_name() {
        return first_name;
    }

    /**Setter method for first_name attribute
     * @param first_name  String value for storing a name of person*/
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Getter method for last_name attribute
     * @return String value that represents last_name
     * */
    public String getLast_name() {
        return last_name;
    }

    /**Setter method for last_name attribute
     * @param last_name String value for storing a surname of person
     * */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Getter method for email attribute
     * @return String value that represents E-mail
     * */
    public String getEmail() {
        return email;
    }

    /**Setter method for email attribute
     * @param email String value for storing an email address
     * */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method for Contact_number attribute
     * @return String value that represents Contact_number
     * */
    public String getContact_number() {
        return contact_number;
    }

    /**Setter method for Contact_number attribute
     * @param contact_number String value for storing a contact_number
     * */
    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    /**Generate String that represents object suitable for printing and other usages
     * @return String value created of attributes of an object
     * */
    @Override
    public String toString() {
        return "ReservationAndGuest{" +
                "date_of_arrival=" + date_of_arrival +
                ", departure_date=" + departure_date +
                ", guest_id=" + guest_id +
                ", room_id=" + room_id +
                ", id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", contact_number='" + contact_number + '\'' +
                '}';
    }
}
