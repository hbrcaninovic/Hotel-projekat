package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.business.GuestManager;
import ba.unsa.etf.rpr.business.ReservationManager;
import ba.unsa.etf.rpr.business.RoomManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.domain.Guest;
import ba.unsa.etf.rpr.domain.Reservation;
import ba.unsa.etf.rpr.domain.Room;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Entry point to CLI application
 */
public class App
{
    private static final Option exit = new Option("e","exit",false,"Exit application");
    private static final Option getAllEmployees = new Option("gae","get-all-employees",false,"Gives a list of all employees");
    private static final Option getAllGuests = new Option("gag","get-all-guests",false,"Gives a list of all guests");
    private static final Option getAllRooms = new Option("gar","get-all-rooms",false,"Gives a list of all rooms");
    private static final Option getAllReservations = new Option("gare","get-all-reservations",false,"Gives a list of all reservations");

    private static final Option addEmployee = new Option("ae","add-employee",true,"Add new employee");
    private static final Option addGuest = new Option("ag","add-guest",true,"Add new guest");
    private static final Option addRoom = new Option("ar","add-room",true,"Add new room");
    private static final Option addReservation = new Option("are","add-reservation",true,"Add new reservation");

    private static final Option updateRoom = new Option("ur","update-room",true,"Update room");
    private static final Option deleteRoom = new Option("dr","delete-room",true,"Delete room");

    private static final EmployeeManager employeeManager = new EmployeeManager();
    private static final RoomManager roomManager = new RoomManager();
    private static final GuestManager guestManager = new GuestManager();
    private static final ReservationManager reservationManager = new ReservationManager();

    public static void main(String[] args) {

        CommandLineParser parser = new DefaultParser();
        
        try {
            CommandLine commandLine = parser.parse(addOptions(), args);

            if (commandLine.hasOption("get-all-employees"))
            {
                List<Employee> employeeList = employeeManager.getAllEmployees();
                System.out.println("------------------- Lista svih uposlenika -------------------");
                for (Employee x:employeeList)
                    System.out.println(x);
            }
            else if (commandLine.hasOption("get-all-guests"))
            {
                List<Guest> guests = DaoFactory.guestDao().getAll();
                System.out.println("------------------- Lista svih gostiju -------------------");
                for (Guest x:guests)
                    System.out.println(x);
            }
            else if (commandLine.hasOption("get-all-rooms"))
            {
                List<Room> rooms = roomManager.getAllRooms();
                System.out.println("------------------- Lista svih soba -------------------");
                for (Room x: rooms)
                    System.out.println(x);
            }
            else if (commandLine.hasOption("get-all-reservations"))
            {
                List<Reservation> reservations = reservationManager.getAllReservations();
                System.out.println("------------------- Lista svih rezervacija -------------------");
                for (Reservation x: reservations)
                    System.out.println(x);
            }
            else if (commandLine.hasOption("add-employee"))
            {
                String id = commandLine.getOptionValue("add-employee");
                List<String> data = commandLine.getArgList();
                Employee employee = new Employee(Integer.parseInt(id),
                        data.get(0),
                        data.get(1),
                        data.get(2),
                        data.get(3),
                        data.get(4),
                        data.get(5),
                        Double.parseDouble(data.get(6)),
                        Integer.parseInt(data.get(7)));
                employeeManager.addEmployee(employee);
                System.out.println("Uspjesno je dodan uposlenik u bazu.");

            }
            else if (commandLine.hasOption("add-guest"))
            {
                String id = commandLine.getOptionValue("add-guest");
                List<String> data = commandLine.getArgList();
                Guest guest = new Guest(Integer.parseInt(id),
                        data.get(0),
                        data.get(1),
                        data.get(2),
                        data.get(3)
                        );
                System.out.println(guest);
                guestManager.addGuest(guest);
                System.out.println("Uspjesno je dodan gost u bazu.");

            }
            else if (commandLine.hasOption("add-room"))
            {
                String id = commandLine.getOptionValue("add-room");
                List<String> data = commandLine.getArgList();
                Room room = new Room(Integer.parseInt(id),
                        Integer.parseInt(data.get(0)),
                        Double.parseDouble(data.get(1)),
                        data.get(2),
                        data.get(3)
                );
                System.out.println(room);
                roomManager.addRoom(room);
                System.out.println("Uspjesno je dodana soba u bazu.");

            }
            else if (commandLine.hasOption("add-reservation"))
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");

                String id = commandLine.getOptionValue("add-reservation");
                List<String> data = commandLine.getArgList();
                Reservation reservation = new Reservation(Integer.parseInt(id),
                        LocalDate.parse(data.get(0), formatter),
                        LocalDate.parse(data.get(1), formatter),
                        Integer.parseInt(data.get(2)),
                        Integer.parseInt(data.get(3))
                );
                System.out.println(reservation);
                reservationManager.addReservation(reservation);
                System.out.println("Uspjesno je dodana rezervacija u bazu.");
            }
            else if (commandLine.hasOption("update-room"))
            {
                String id = commandLine.getOptionValue("update-room");
                List<String> data = commandLine.getArgList();
                Room room = new Room(Integer.parseInt(id),
                        Integer.parseInt(data.get(0)),
                        Double.parseDouble(data.get(1)),
                        data.get(2),
                        data.get(3)
                );
                System.out.println(room);
                if (roomManager.updateRoom(room)) System.out.println("Uspjesno je azurirana soba u bazi.");
                else System.out.println("Soba nije uspjesno azurirana!");

            }
            else if (commandLine.hasOption("delete-room"))
            {
                String id = commandLine.getOptionValue("delete-room");
                Room room = roomManager.getRoom(Integer.parseInt(id));
                System.out.println("Soba koja se brise iz baze: "+room);
                if (roomManager.deleteRoom(room)) System.out.println("Uspjesno je izbrisana soba iz bazi.");
                else System.out.println("Soba nije uspjesno izbrisana iz baze!");

            }
            else {
                printHelp();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    private static void printHelp() {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 100, "java -jar Hotel-projekat-cli-jar-with-dependencies.jar [option] [arg]");
        helpFormatter.printOptions(printWriter, 100, addOptions(), 2, 5);
        printWriter.close();
    }

    private static Options addOptions() {
        Options options = new Options();
        options.addOption(getAllEmployees);
        options.addOption(getAllGuests);
        options.addOption(getAllRooms);
        options.addOption(getAllReservations);
        options.addOption(addEmployee);
        options.addOption(addGuest);
        options.addOption(addRoom);
        options.addOption(addReservation);
        options.addOption(updateRoom);
        options.addOption(deleteRoom);
        return options;
    }

}
