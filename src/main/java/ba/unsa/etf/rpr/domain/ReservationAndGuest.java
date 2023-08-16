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
    public ReservationAndGuest(){
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public LocalDate getDate_of_arrival() {
        return date_of_arrival;
    }

    public void setDate_of_arrival(LocalDate date_of_arrival) {
        this.date_of_arrival = date_of_arrival;
    }

    public LocalDate getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(LocalDate departure_date) {
        this.departure_date = departure_date;
    }

    public int getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(int guest_id) {
        this.guest_id = guest_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

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
