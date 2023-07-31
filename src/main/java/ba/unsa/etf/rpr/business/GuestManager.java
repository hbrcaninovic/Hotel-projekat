package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Guest;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;

public class GuestManager {

    public boolean addGuest(Guest guest) throws HotelExceptions {
        try {
            DaoFactory.guestDao().add(guest);
            return true;
        }
        catch (Exception m)
        {
            return true;
        }
    }
}
