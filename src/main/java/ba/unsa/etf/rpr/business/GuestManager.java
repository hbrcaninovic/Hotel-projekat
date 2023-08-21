package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Guest;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;

public class GuestManager {

    public Guest getGuest(int guestID) throws HotelExceptions {
        return DaoFactory.guestDao().getGuestById(guestID);
    }

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

    public boolean deleteGuest(Guest guest){
        try {
            DaoFactory.guestDao().deleteGuest(guest.getId());
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean updateGuest(Guest guest) {
        try {
            DaoFactory.guestDao().update(guest);
            return true;
        }
        catch (HotelExceptions e) {
            return false;
        }
    }

}
