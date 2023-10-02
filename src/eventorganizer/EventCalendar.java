package eventorganizer;

import java.sql.SQLOutput;

/** EventCalendar class holds the events and prints when called
 * consists of 10 methods
 * EventCalendar(){}
 * Initializes the events array
 * find(){}
 * used to find an event in the events array
 * grow(){}
 * used to increase the events array when it reaches capacity
 * add(){}
 * Adds an event to the events array
 * remove(){}
 * removes an event from the events array
 * contains(){}
 * used to check if the events array contains an event
 * print(){}
 * Prints the events in events array
 * printByDate(){}
 * Prints the events sorted by date and timeslot
 * printByCampus(){}
 * Prints the events sorted by campus
 * printByDepartment(){}
 * Prints the events sorted by department
 *
 * @author David Rahabi, Judah Farkas
 */
public class EventCalendar {
    private Event [] events; //the array holding the list of events
    private int numEvents; //current number of events in the array
    private int size; // size of the array

    /** EventCalendar constructs the events array
     * gives it a size of 4 starting off
     * sets the number of events to 0
     */
    public EventCalendar(){
        this.size = 4;
        this.events = new Event[size];
        this.numEvents = 0;

    }

    /** Find event in the events array
     * @param event the event to find
     * @return -1 if event is not found, otherwise return the array value of event
     */
    private int find(Event event) {
        final int NOT_FOUND = -1;
    for (int i = 0; i < numEvents; i++){
        if (events[i].equals(event)){
            return i;
        }
        
        }
        return NOT_FOUND;
    }

    /** Grow increases the events array by 4 if it reaches max capacity
     */
    private void grow() { //increase the capacity by 4
        this.size = size + 4;
        Event[] growEvents = new Event[size];

        for (int i = 0; i < numEvents; i++){
            growEvents[i] = events[i];

        }
        this.events = growEvents;

    }

    /** Adds event to events array
     * this method also checks if the event has valid parameters to add the event to the array
     * checks for:
     * if the event is already in the array
     * valid date
     * valid contact information
     * valid duration
     * valid array size, if not, increase array size
     * @param event event to add
     * @return true ; event added, false if invalid date, contact, duration, or event is already added
     */
    public boolean add(Event event) {
        if(numEvents>0 && find(event)!=-1){
            System.out.println("The event is already on the calendar.");
            return false;
        }
        if(!event.getDate().isValid()){
            return false;
        }
        if(!event.getContact().isValid()){
            System.out.println("Invalid contact information!");
            return false;
        }
        if(event.getDuration()>120 || event.getDuration()<30 ){
            System.out.println("Event duration must be at least 30 minutes and at most 120 minutes");
            return false;
        }
        if (this.numEvents == this.size){
            grow();
        }
        this.events[numEvents] = event;
        this.numEvents++;
        System.out.println("Event added to calendar!");
        return true;
    }


    /** Removes event to events array
     * this method also checks if the event has valid parameters to remove
     * checks for:
     * if the event is in the array
     * @param event event to remove
     * @return true ; event added, false if event is not in the events array
     */
    public boolean remove(Event event) {
        if(!event.getDate().isValid()){
            return false;
        }

        int rm = find(event);
        if(find(event)==-1){
            System.out.println("Cannot remove; event is not in the calendar!");
            return false;
        }

        Event[] removeEvent = new Event[size];
        int count=0;
        for (int i = 0; i < numEvents; i++){
            if (i != rm){
                removeEvent[count] = events[i];
                count++;
            }
            else{
                continue;
            }
        }
        this.events = removeEvent;
        this.numEvents--;
        System.out.println("Event has been removed from the calendar!");
        return true;
     }


    /** Contains method checks if the event is in the events array
     * boolean method
     * @param event event to check
     * @return true if event is in array, false if not
     */
    public boolean contains(Event event)  {
        boolean eventInArray=false;
        for(int i=0; i < numEvents; i++){
           if(event.equals(events[i])){
            eventInArray=true;
           }
        }
        return eventInArray;
    }

    /** Prints array in input order
     */
    public void print() {
        if (numEvents == 0) {
            System.out.println("Event calender is empty!");
        }
        else {
            System.out.println("* Event calendar *");
            if (numEvents == 0) {
                System.out.println("Event calender is empty!");
            } else {
                for (int i = 0; i < this.numEvents; i++) {
                    System.out.println(events[i].toString());
                }
            } //print the array as is
            System.out.println("* end of event calendar *");
        }
    }

    /** Prints array sorted by date and timeslot
     */
    public void printByDate() {
        if (numEvents == 0) {
            System.out.println("Event calender is empty!");
        } else {
            System.out.println("* Event calendar by event date and start time *");
            for (int i = 0; i < numEvents; i++) { //sorting the events array by date using selection sort
                Event earliestEvent = events[i];
                int placeToSwitch = i;
                for (int p = i + 1; p < numEvents; p++) {
                    if (events[p].compareTo(earliestEvent) < 0) {
                        earliestEvent = events[p];
                        placeToSwitch = p;
                    }
                }
                Event temp = events[placeToSwitch];
                events[placeToSwitch] = events[i];
                events[i] = temp;
            } // events is now sorted
            for (int i = 0; i < numEvents; i++) {
                System.out.println(events[i].toString());
            }
            System.out.println("* end of event calendar *");
        }
    }

    /** Prints array sorted by campus
     */
    public void printByCampus() {
        if (numEvents == 0) {
            System.out.println("Event calender is empty!");
        } else {
            System.out.println("* Event calendar by campus and building *");
            for (int i = 0; i < numEvents; i++) { //sorting the events array by date using selection sort
                Event firstCampus = events[i];
                int placeToSwitch = i;
                for (int p = i + 1; p < numEvents; p++) {
                    if (events[p].getLocation().compareTo(firstCampus.getLocation()) < 0) {
                        firstCampus = events[p];
                        placeToSwitch = p;
                    }
                }
                Event temp = events[placeToSwitch];
                events[placeToSwitch] = events[i];
                events[i] = temp;
            }
            for (int i = 0; i < numEvents; i++) {
                System.out.println(events[i].toString());
            }
            System.out.println("* end of event calendar *");
        }
    } //ordered by campus and building/room


    /** Prints array sorted by department
     */
    public void printByDepartment() {

        if (numEvents == 0) {
            System.out.println("Event calender is empty!");
        } else {
            System.out.println("* Event calendar by department *");
            for (int i = 0; i < numEvents; i++) { //sorting the events array by date using selection sort
                Event firstDepartment = events[i];
                int placeToSwitch = i;
                for (int p = i + 1; p < numEvents; p++) {
                    if (events[p].getContact().getDepartment().compareTo(firstDepartment.getContact().getDepartment()) < 0) {
                        firstDepartment = events[p];
                        placeToSwitch = p;
                    }
                }
                Event temp = events[placeToSwitch];
                events[placeToSwitch] = events[i];
                events[i] = temp;
            }
            for (int i = 0; i < numEvents; i++) {
                System.out.println(events[i].toString());
            }
            System.out.println("* end of event calendar *");

        } //ordered by department

    }

    /*public Event[] sortByCampus(Event[] array){
        Event[] sortedByCampus=new Event[numEvents];
        int numElements_InSortedEvents = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < numEvents; j++) {
                if (i == 0 && events[j].getLocation().getCampus().equals("Busch") && events[j].getLocation().getBuildingName().equals("Allison Road Classroom")) {
                    sortedByCampus[numElements_InSortedEvents] = events[j];
                    numElements_InSortedEvents++;
                }
                else if(i == 1 && events[j].getLocation().getCampus().equals("Busch") && events[j].getLocation().getBuildingName().equals("Hill Center")){
                    sortedByCampus[numElements_InSortedEvents] = events[j];
                    numElements_InSortedEvents++;
                }
                else if(i==2 && events[j].getLocation().getCampus().equals("College Avenue") && events[j].getLocation().getBuildingName().equals("Academic Building")){
                    sortedByCampus[numElements_InSortedEvents] = events[j];
                    numElements_InSortedEvents++;
                }
                else if (i == 3 && events[j].getLocation().getCampus().equals("College Avenue") && events[j].getLocation().getBuildingName().equals("Murray Hall")) {
                    sortedByCampus[numElements_InSortedEvents] = events[j];
                    numElements_InSortedEvents++;
                } else if (i == 4 && events[j].getLocation().getCampus().equals("Livingston") && events[j].getLocation().getBuildingName().equals("Beck Hall")) {
                    sortedByCampus[numElements_InSortedEvents] = events[j];
                    numElements_InSortedEvents++;
                }
                else if (i == 5 && events[j].getLocation().getCampus().equals("Livingston") && events[j].getLocation().getBuildingName().equals("Tillett Hall")){
                    sortedByCampus[numElements_InSortedEvents] = events[j];
                    numElements_InSortedEvents++;
                }
            }
        }
        return sortedByCampus;
    }*/
   /* public Event[] sortByDepartment(Event[] array){
        Event[] sortedDepartment=new Event[numEvents];
        int numElements_InSortedDepartment=0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < numEvents; j++) {
                if (i == 0) {
                    if (events[j].getContact().getDepartment().getDepartmentName().equals("Business Analytics and Information Technology")) {
                        sortedDepartment[numElements_InSortedDepartment] = events[j];
                        numElements_InSortedDepartment++;
                    }
                } else if (i == 1) {
                    if (events[j].getContact().getDepartment().getDepartmentName().equals("Computer Science")) {
                        sortedDepartment[numElements_InSortedDepartment] = events[j];
                        numElements_InSortedDepartment++;
                    }
                } else if (i == 2) {
                    if (events[j].getContact().getDepartment().getDepartmentName().equals("Electrical Engineering")) {
                        sortedDepartment[numElements_InSortedDepartment] = events[j];
                        numElements_InSortedDepartment++;
                    }
                } else if (i == 3) {
                    if (events[j].getContact().getDepartment().getDepartmentName().equals("Information Technology and Informatics")) {
                        sortedDepartment[numElements_InSortedDepartment] = events[j];
                        numElements_InSortedDepartment++;
                    }
                } else if (i == 4) {
                    if (events[j].getContact().getDepartment().getDepartmentName().equals("Mathematics")) {
                        sortedDepartment[numElements_InSortedDepartment] = events[j];
                        numElements_InSortedDepartment++;
                    }
                }
            }
        }
        return sortedDepartment;
    }*/



    }
