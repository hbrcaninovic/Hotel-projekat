package ba.unsa.etf.rpr.dao;


public class DaoFactory {
    private static final EmployeeDao employeeDao = EmployeeDaoSQLImpl.getInstance();
    private static final GuestDao guestDao = GuestDaoSQLImpl.getInstance();
    private static final ReservationDao reservationDao = ReservationDaoSQLImpl.getInstance();
    private static final RoomDao roomDao = RoomDaoSQLImpl.getInstance();

    /** Implicit constructor */
    public DaoFactory() {

    }

    /** Gets an instance EmployeeDao */
    public static EmployeeDao employeeDao(){
        return employeeDao;
    }

    /** Gets an instance GuestDao */
    public static GuestDao guestDao(){
        return guestDao;
    }

    /** Gets an instance ReservationDao */
    public static ReservationDao reservationDao(){
        return  reservationDao;
    }

    /** Gets an instance RoomDao */
    public static RoomDao roomDao(){
        return roomDao;
    }
}

