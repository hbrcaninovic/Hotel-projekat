package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Guest;

import java.util.Date;
import java.util.List;

public interface GuestDao extends Dao<Guest>{

    /**
     * Searches for guests who have arrived or made a reservation in the interval between two dates, including submitted dates.
     * @param date1 is a date of arriving (check-in date)
     * @param date2 check-out date
     * @return List of guests who stayed in the hotel from date1 to date2 */
    List<Guest> getByDateRange(Date date1, Date date2);
}
