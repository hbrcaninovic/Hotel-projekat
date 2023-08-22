package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.exceptions.HotelExceptions;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {



        while(true){
            System.out.flush();
            System.out.println("-------------------- Dobrodošli u HOME sistem --------------------");
            System.out.println("Odaberite jednu od sljedećih opcija:");
            System.out.println("   1. Prijava u sistem");
            System.out.println("   2. Izlaz iz aplikacije\n");
            System.out.println(" Unesite broj za odabir opcije: ");
            Scanner unos = new Scanner(System.in);
            int opcija = unos.nextInt();

            switch (opcija){
                case 1:
                    System.out.flush();
                    System.out.println("Prijava");
                    break;
                case 2:
                    System.exit(0);

            }

        }
      /*  LocalDate d=LocalDate.of(2020,2,15);
        Employee e=new Employee(11,"test1","t2","TEST","TEST","adress","mail",d,"decorator",1220,0);
        try {
            DaoFactory.employeeDao().add(e);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }


       */
/*
            System.out.println("LogIn test");
            System.out.println(DaoFactory.employeeDao().getAdminStatusByUsernameAndPassword("worker1","w123"));
            System.out.println(DaoFactory.employeeDao().getAdminStatusByUsernameAndPassword("admin","a123"));
        System.out.println(DaoFactory.employeeDao().getAdminStatusByUsernameAndPassword("adn","a123"));


 */
      /* System.out.println("Delete test");
        RoomManager rm = new RoomManager();
        try {
           DaoFactory.roomDao().delete(151);
            System.out.println("Brisanje zavrseno");
        }
        catch (HotelExceptions e) {
            System.out.println(e.getMessage());
            System.out.println(e.toString());
        }


       */
/*
        LocalDate d1 = LocalDate.parse("2023-07-31");
        try {
            Reservation reservation = new Reservation(DaoFactory.reservationDao().getMaxReservationId()+1,d1,d1,5,130);

            DaoFactory.reservationDao().add(reservation);
        } catch (HotelExceptions e) {
            System.out.println(e.getMessage());
            System.out.println(e);
        }


 */

    }

}
