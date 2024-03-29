package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDao extends Dao<Reservation>{

    /**
     * Searches for reservations that have been made in interval between two dates, including submitted dates.
     * @param date1 is a date of the earliest reservation
     * @param date2 is a date of the latest reservation
     * @return List of reservations made from date1 to date2 */
    List<Reservation> getByDateRange(LocalDate date1, LocalDate date2) throws HotelExceptions;

    /** Gives the max value of the number of reservations in the database */
    Integer getMaxReservationId() throws HotelExceptions;

    /** Deletes a Reservation from the database based on the id parameter
     * @param id int value that uniquely define Reservation
     * */
    void deleteReservation(int id) throws HotelExceptions;
}
