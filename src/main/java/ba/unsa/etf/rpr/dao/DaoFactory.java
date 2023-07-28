package ba.unsa.etf.rpr.dao;


public class DaoFactory {
    private static final EmployeeDao employeeDao = EmployeeDaoSQLImpl.getInstance();
    private static final GuestDao guestDao = GuestDaoSQLImpl.getInstance();
    private static final ReservationDao reservationDao = ReservationDaoSQLImpl.getInstance();
    private static final RoomDao roomDao = RoomDaoSQLImpl.getInstance();

    public DaoFactory() {

    }

    public static EmployeeDao employeeDao(){
        return employeeDao;
    }

    public static GuestDao guestDao(){
        return guestDao;
    }

    public static ReservationDao reservationDao(){
        return  reservationDao;
    }

    public static RoomDao roomDao(){
        return roomDao;
    }
}

