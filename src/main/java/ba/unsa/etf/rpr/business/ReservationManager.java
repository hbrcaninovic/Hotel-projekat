package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Guest;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;
import javafx.collections.ObservableList;

import java.util.List;

public class ReservationManager {

    /** Gives the max value of the number of reservations in the database */
    public Integer getMaxReservationId() throws HotelExceptions {
        return DaoFactory.reservationDao().getMaxReservationId();
    }

    /** Adds a reservation to the database
     * @param reservation Reservation object that represent reservation
     * @return boolean value which gives confirmation of the successful addition of the reservation to the database
     * */
    public boolean addReservation(Reservation reservation) throws HotelExceptions {
        try {
            DaoFactory.reservationDao().add(reservation);
            return true;
        }
        catch (Exception m)
        {
            return true;
        }
    }

    /** A method that returns a list of all reservations from the database
     * @return List of Reservation
     * */
    public List<Reservation> getAllReservations() throws HotelExceptions {
        return DaoFactory.reservationDao().getAll();
    }

    /** Deletes the specified reservation from the database
     * @param reservation Reservation object that deletes
     * @return boolean value which gives confirmation of the successful deletion of the reservation to the database
     * */
    public boolean deleteReservation(Reservation reservation){
        try {
            DaoFactory.reservationDao().deleteReservation(reservation.getId());
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /** Updates the specified reservation from the database
     * @param reservation Reservation object that updates
     * @return boolean value which gives confirmation of the successful update of the reservation to the database
     * */
    public boolean updateReservation(Reservation reservation) {
        try {
            DaoFactory.reservationDao().update(reservation);
            return true;
        }
        catch (HotelExceptions e) {
            return false;
        }
    }


}
