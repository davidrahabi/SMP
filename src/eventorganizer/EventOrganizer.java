package eventorganizer;
import java.sql.SQLOutput;
import java.util.Scanner;

public class EventOrganizer{
    /**
     * add package header to every file
     * redo eventorganizer contructor and take out eventcalendar instance variable
     * declare eventcalendar variable inside run() function
     */


    EventOrganizer(){}

    public void run(){
        EventCalendar calendar= new EventCalendar();
        Scanner scanner = new Scanner(System.in);
        System.out.println ("Event Organizer running....");
        while (true){
            String input = scanner.next();
            if (input.equals("Q")){
                System.out.println ("Event Organizer terminated");
                break; }
            switch(input){
                case "A": case "R":
                    String[] dateNums=scanner.next().split("/");
                    Date date = new Date(Integer.parseInt(dateNums[0]), Integer.parseInt(dateNums[1]), Integer.parseInt(dateNums[2]));
                    Timeslot timeslot=createTimeslot(scanner.next());
                    if(timeslot==null){ scanner.nextLine(); //going to next input line
                                        break;       }
                    Location location = createLocation(scanner.next());
                    if(location==null){ scanner.nextLine();
                                        break;       }
                    switch(input){
                        case "A":
                            Department department = createDepartment(scanner.next());
                            if(department==null){ scanner.nextLine();
                                                break;     }
                            Contact contact = new Contact(department, scanner.next());
                           calendar.add(new Event(date,timeslot,location, contact, scanner.nextInt()));

                            break;
                        case "R":
                            calendar.remove(new Event(date,timeslot,location));}
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
                    System.out.println(input + " is an invalid command!");
                    break;
            }}}

    public Timeslot createTimeslot(String time){
        if(time.toLowerCase().equals("afternoon")) return Timeslot.AFTERNOON;
        else if(time.toLowerCase().equals("morning")) return Timeslot.MORNING;
        else if(time.toLowerCase().equals("evening"))return Timeslot.EVENING;
        System.out.println("Invalid time slot!");
        return null;
                    
    }

    public Location createLocation(String location){
        if(location.toLowerCase().equals("hll114")) return Location.HLL114;
        else if(location.toLowerCase().equals("arc103")) return Location.ARC103;
        else if(location.toLowerCase().equals("be_aud")) return Location.BE_AUD;
        else if(location.toLowerCase().equals("til232")) return Location.TIL232;
        else if(location.toLowerCase().equals("ab2225")) return Location.AB2225;
        else if(location.toLowerCase().equals("mu302")) return Location.MU302;
        System.out.println("Invalid Location!");
        return null;

    }

    public Department createDepartment(String department){
        if(department.toLowerCase().equals("cs")) return Department.CS;
        else if(department.toLowerCase().equals("ee")) return Department.EE;
        else if(department.toLowerCase().equals("iti")) return Department.ITI;
        else if(department.toLowerCase().equals("math")) return Department.MATH;
        else if(department.toLowerCase().equals("bait")) return Department.BAIT;
        System.out.println("Invalid Department!");
        return null;

    }
    
}

