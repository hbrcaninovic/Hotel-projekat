package ba.unsa.etf.rpr;

import java.util.Date;
import java.util.List;

public interface ReservationDao extends Dao<Reservation>{

    /**
     * Searches for reservations that have been made in interval between two dates, including submitted dates.
     * @param date1 is a date of the earliest reservation
     * @param date2 is a date of the latest reservation
     * @return List of reservations made from date1 to date2 */
    List<Reservation> getByDateRange(Date date1, Date date2);

}
