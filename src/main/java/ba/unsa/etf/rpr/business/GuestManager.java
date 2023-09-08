package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Guest;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;

public class GuestManager {

    /** Gives information about the guest from the database based on the guest id
     * @param guestID int value that represents guest id number
     * @return Guest value that id is same as given
     * */
    public Guest getGuest(int guestID) throws HotelExceptions {
        return DaoFactory.guestDao().getGuestById(guestID);
    }

    /** Adds a guest to the database
     * @param guest Guest object that represent guest
     * @return boolean value which gives confirmation of the successful addition of the guest to the database
     * */
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

    /** Deletes the specified guest from the database
     * @param guest Guest object that deletes
     * @return boolean value which gives confirmation of the successful deletion of the guest to the database
     * */
    public boolean deleteGuest(Guest guest){
        try {
            DaoFactory.guestDao().deleteGuest(guest.getId());
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /** Updates the specified guest from the database
     * @param guest Guest object that updates
     * @return boolean value which gives confirmation of the successful update of the guest to the database
     * */
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
