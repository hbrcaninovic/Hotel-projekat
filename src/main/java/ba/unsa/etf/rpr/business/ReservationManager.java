package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;
import javafx.collections.ObservableList;

import java.util.List;

public class ReservationManager {

    public Integer getMaxReservationId() throws HotelExceptions {
        return DaoFactory.reservationDao().getMaxReservationId();
    }

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

    public List<Reservation> getAllReservations() throws HotelExceptions {
        return DaoFactory.reservationDao().getAll();
    }




}
