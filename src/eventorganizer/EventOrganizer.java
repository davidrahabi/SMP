package eventorganizer;

import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * The goal of this class is to take all the commands inputs and return the correct outputs, while also checking for invalid inputs
 * This class uses 4 methods, run();, createTimeslot();, createLocation();, and createDepartment();
 * the run(); method takes the command inputs and determines which classes/files to call.
 * It also determines if a command is invalid.
 * createTimeslot();, createLocation();, and createDepartment(); calls the Enum classes to determine if the Enum inputs are valid
 *
 * @author David Rahabi, Judah Farkas
 */
public class EventOrganizer {

    EventOrganizer() {
    }

    /**
     * Begins scanner, reads the inputted commands, and executes
     * Commands are as follows:
     * A: adds event
     * R: removes event
     * P: Prints all events in the array in inputted order
     * PE: Prints events sorted by date
     * PC: Prints events ordered by campus
     * PD: Prints events sorted by department
     * Q: terminated program
     * all other commands should print as an invalid command
     */
    public void run() {
        EventCalendar calendar = new EventCalendar();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Event Organizer running....");
        while (true) {
            String command = scanner.next();
            String input = scanner.nextLine();
            if (command.equals("Q")) {
                System.out.println("Event Organizer terminated");
                break;
            }
            switch (command) {
                case "A":
                    if (processAdd(input) != null) {
                        calendar.add(processAdd(input));
                    }
                    break;
                case "R":
                    if (processRemove(input) != null) {
                        calendar.remove(processRemove(input));
                    }
                    break;
                case "P":
                    calendar.print();
                    break;
                case "PE":
                    calendar.printByDate();
                    break;
                case "PC":
                    calendar.printByCampus();
                    break;
                case "PD":
                    calendar.printByDepartment();
                    break;
                default:
                    System.out.println(command + " is an invalid command!");
                    break;
            }
        }
    }

    /**
     * processAdd takes input as a string and makes it into an array of elements to be used to create an event
     * returns null if invalid timeslot, location or department
     *
     * @param input the scanner input string
     * @return event unless timeslot, location, or department is invalid
     */
    public Event processAdd(String input) {
        String[] inputArray = input.trim().split("\\s+");
        Date date = new Date(inputArray[0]);
        Timeslot timeslot = createTimeslot(inputArray[1]);
        if (timeslot == null) { //going to next input line
            return null;
        }
        Location location = createLocation(inputArray[2]);
        if (location == null) {
            return null;
        }
        Department department = createDepartment(inputArray[3]);
        if (department == null) {
            return null;
        }
        Contact contact = new Contact(department, inputArray[4]);
        int duration = Integer.parseInt(inputArray[5]);
        return new Event(date, timeslot, location, contact, duration);
    }

    /**
     * processAdd takes input as a string and makes it into an array of elements tobe used to remove an event
     * returns null if invalid timeslot or location
     *
     * @param input the scanner input string
     * @return event unless timeslot, location, or department is invalid
     */
    public Event processRemove(String input) {
        String[] inputArray = input.trim().split("\\s+");
        Date date = new Date(inputArray[0]);
        Timeslot timeslot = createTimeslot(inputArray[1]);
        if (timeslot == null) { //going to next input line
            return null;
        }
        Location location = createLocation(inputArray[2]);
        if (location == null) {
            return null;
        }
        return new Event(date, timeslot, location);
    }

    /**
     * isValidEmail validated the email ensuring it is [USER}@rutgers.edu
     *
     * @param email users email
     * @return true , if email is invalid return false
     */
    public boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }


        int amountOfAts = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                if (i == 0 || i == email.length() - 1) {
                    return false;
                }
                amountOfAts++;
            }
        }
        if (amountOfAts > 1 || amountOfAts == 0) return false;
        else {
            String[] emailSplit = email.split("@");
            if (!emailSplit[1].equals("rutgers.edu")) return false;
        }
        return true;
    }

    /**
     * createTimeslot takes the inputted timeslot from the run(); function and returns the Enum value
     * returns null if invalid Enum
     *
     * @param time the timeslot input string
     * @return correct timeslot Enum value
     */

    public Timeslot createTimeslot(String time) {
        if (time.toLowerCase().equals("afternoon")) return Timeslot.AFTERNOON;
        else if (time.toLowerCase().equals("morning")) return Timeslot.MORNING;
        else if (time.toLowerCase().equals("evening")) return Timeslot.EVENING;
        System.out.println("Invalid time slot!");
        return null;
    }

    /**
     * createLocation takes the inputted location from the run(); function and returns the Enum value
     * returns null if invalid Enum
     *
     * @param location; the location input string
     * @return correct location Enum value
     */

    public Location createLocation(String location) {
        if (location.toLowerCase().equals("hll114")) return Location.HLL114;
        else if (location.toLowerCase().equals("arc103")) return Location.ARC103;
        else if (location.toLowerCase().equals("be_aud")) return Location.BE_AUD;
        else if (location.toLowerCase().equals("til232")) return Location.TIL232;
        else if (location.toLowerCase().equals("ab2225")) return Location.AB2225;
        else if (location.toLowerCase().equals("mu302")) return Location.MU302;
        System.out.println("Invalid Location!");
        return null;

    }

    /**
     * createDepartment takes the inputted department from the run(); function and returns the Enum value
     * returns null if invalid Enum
     *
     * @param department; the department input string
     * @return correct department Enum value
     */

    public Department createDepartment(String department) {
        if (department.toLowerCase().equals("cs")) return Department.CS;
        else if (department.toLowerCase().equals("ee")) return Department.EE;
        else if (department.toLowerCase().equals("iti")) return Department.ITI;
        else if (department.toLowerCase().equals("math")) return Department.MATH;
        else if (department.toLowerCase().equals("bait")) return Department.BAIT;
        System.out.println("Invalid contact information!");
        return null;

    }

}

