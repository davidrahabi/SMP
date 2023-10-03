package eventorganizer;

/**
 * Event class inputs and stores the different values for an event
 * this class contains 10 methods
 * equals()
 * if the object equals the event
 * toString()
 * inputs the event to a string
 * compareTo()
 * compares the event with the date and start time
 * 2 Event() classes
 * constructs an event for add and remove
 * getContact(), getDate(), getTimeslot(), getLocation(), getDuration();
 * getter methods for contact, date,timeslot, location, and durration
 *
 * @author Judah Farkas, David Rahabi
 */
public class Event implements Comparable<Event> {
    private Date date; //the event date
    private Timeslot startTime; //the starting time
    private Location location;
    private Contact contact; //include the department name and email
    private int duration; //in minutes


    /**
     * overrides java equals method
     *
     * @param obj obj
     * @return true if equal, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event event) {
            return event.date.equals(this.date) && event.startTime.equals(this.startTime) && event.location.equals(this.location);

        }
        return false;
    }

    /**
     * Overides java method toString
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[Event Date: " + date.getMonth() + "/" + date.getDay() + "/" + date.getYear() + "] [Start: " +
                startTime.getTime() + "] [End: " + startTime.getEndTime(duration) + "]  @" + location.name() + " ("
                + location.getBuildingName() + "," + location.getCampus() + ") [Contact: " +
                contact.getDepartment().getDepartmentName() + ", " + contact.getEmail() + "]";

        // [Event Date: 10/21/2023] [Start: 2:00pm] [End: 3:00pm] @HLL114 (Hill Center, Busch) [Contact: Computer Science, cs@rutgers.edu]
    }

    /**
     * overides compareTo java method
     *
     * @param event event
     * @return 1 if event date or star time is 1, -1 if event date or start timeis -1
     */
    @Override
    public int compareTo(Event event) {
        if (this.date.compareTo(event.date) == 1) return 1;
        else if (this.date.compareTo(event.date) == -1) return -1;
        else {
            if (this.startTime.compareTime(event.startTime) == 1) return 1;
            else if (this.startTime.compareTime(event.startTime) == -1) return -1;
            else return 0;
        }
    }

    /**
     * Creates event for adding
     *
     * @param date      date
     * @param startTime start time
     * @param location  location
     * @param contact   contact
     * @param duration  duration
     */
    Event(Date date, Timeslot startTime, Location location, Contact contact, int duration) {
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.contact = contact;
        this.duration = duration;
    }

    /**
     * Creates event for removing
     *
     * @param date      date
     * @param startTime start time
     * @param location  location
     */
    Event(Date date, Timeslot startTime, Location location) {
        this.date = date;
        this.startTime = startTime;
        this.location = location;
    }


    /**
     * Getter method for contact
     *
     * @return this.contact
     */
    public Contact getContact() {
        return this.contact;
    }

    /**
     * getter method for date
     *
     * @return this.date
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Getter method for timeslot
     *
     * @return this.timeslot
     */
    public Timeslot getTimeslot() {
        return this.startTime;
    }

    /**
     * Getter method for location
     *
     * @return this.location
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * getter method for duration
     *
     * @return this.duration
     */
    public int getDuration() {
        return this.duration;
    }

    /** Main method for testing
     *
     * @param args args
     */
    public static void main(String[] args) {
        testEventEquals();
        testNotEqualsDate();
        testNotEqualsTimeslot();
        testNotEqualsLocation();
    }

    /**
     * Test Case #1
     * Tests if event is = to each other
     */
    private static void testEventEquals() {
        Date date = new Date(12, 15, 2023);
        Timeslot timeslot = Timeslot.MORNING;
        Location location = Location.AB2225;
        Event event = new Event(date, timeslot, location);
        Event equalEvent = new Event(date, timeslot, location);
        boolean expectedOutput = true;
        boolean actualOutput = event.equals(equalEvent);
        System.out.println("**Test case #1: Checks if event is = to each other");
        testResult(event, expectedOutput, actualOutput);
    }

    /**
     * Test Case #2
     * Tests if event is != to each other (date changed)
     */
    private static void testNotEqualsDate() {
        Date date = new Date(10, 15, 2023);
        Timeslot timeslot = Timeslot.MORNING;
        Location location = Location.AB2225;
        Date notEqualsDate = new Date(11, 15, 2023);
        Event event = new Event(date, timeslot, location);
        Event notEqualsEvent = new Event(notEqualsDate, timeslot, location);
        boolean expectedOutput = false;
        boolean actualOutput = event.equals(notEqualsEvent);
        System.out.println("**Test case #2: Checks if date is != to each other");
        testResult(event, expectedOutput, actualOutput);
    }

    /**
     * Test Case #3
     * Tests if event is != to each other (Timeslot Changed)
     */
    private static void testNotEqualsTimeslot() {
        Date date = new Date(10, 15, 2023);
        Timeslot timeslot = Timeslot.MORNING;
        Location location = Location.AB2225;
        Timeslot notEqualsTimeslot = Timeslot.AFTERNOON;
        Event event = new Event(date, timeslot, location);
        Event notEqualsEvent = new Event(date, notEqualsTimeslot, location);
        boolean expectedOutput = false;
        boolean actualOutput = event.equals(notEqualsEvent);
        System.out.println("** Test case #3: Checks if TimeSlot is != to each other");
        testResult(event, expectedOutput, actualOutput);
    }

    /**
     * Test Case #4
     * Tests if event is != to each other (Location Changed)
     */
    private static void testNotEqualsLocation() {
        Date date = new Date(10, 15, 2023);
        Timeslot timeslot = Timeslot.MORNING;
        Location location = Location.AB2225;
        Location notEqualsLocation = Location.TIL232;
        Event event = new Event(date, timeslot, location);
        Event notEqualsEvent = new Event(date, timeslot, notEqualsLocation);
        boolean expectedOutput = false;
        boolean actualOutput = event.equals(notEqualsEvent);
        System.out.println("** Test case #4: Checks if location is != to each other");
        testResult(event, expectedOutput, actualOutput);
    }

    /**
     * Test Result Method
     *
     * @param event          Event
     * @param expectedOutput Expected Output
     * @param actualOutput   Actual Output
     */
    public static void testResult(Event event, boolean expectedOutput, boolean actualOutput) {
        if (expectedOutput == actualOutput) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }
    }
}


