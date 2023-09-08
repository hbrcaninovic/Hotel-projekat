package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Guest;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface GuestDao extends Dao<Guest>{

    /**
     * Searches for guests who have arrived or made a reservation in the interval between two dates, including submitted dates.
     * @param date1 is a date of arriving (check-in date)
     * @param date2 check-out date
     * @return List of guests who stayed in the hotel from date1 to date2 */
    List<Guest> getByDateRange(LocalDate date1, LocalDate date2);

    /**
     * Gives a guest which id is same as given.
     * @param id int that represents guest id
     * @return Guest objects whose id is as same as given
     */
    Guest getGuestById(int id) throws HotelExceptions;

    /** Deletes a Guest from the database based on the id parameter
     * @param id int value that uniquely define Guest
     * */
    void deleteGuest(int id) throws HotelExceptions;
}
