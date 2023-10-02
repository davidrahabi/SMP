package eventorganizer;

import java.sql.SQLOutput;

public class EventCalendar {
    private Event [] events; //the array holding the list of events
    private int numEvents; //current number of events in the array
    private int size; // size of the array

    public EventCalendar(){
        this.size = 4;
        this.events = new Event[size];
        this.numEvents = 0;

    }

    private int find(Event event) {
        final int NOT_FOUND = -1;
    for (int i = 0; i < numEvents; i++){
        if (events[i].equals(event)){
            return i;
        }
        
        }
        return NOT_FOUND;
    } 
    private void grow() { //increase the capacity by 4
        this.size = size + 4;
        Event[] growEvents = new Event[size];

        for (int i = 0; i < numEvents; i++){
            growEvents[i] = events[i];

        }
        this.events = growEvents;

    } 

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


    public boolean contains(Event event) { 
        boolean eventInArray=false;
        for(int i=0; i < numEvents; i++){
           if(event.equals(events[i])){
            eventInArray=true;
           }
        }
        return eventInArray;
    }
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
    public void printByDate() {
        if (numEvents == 0) {
            System.out.println("Event calender is empty!");
        } else {
            System.out.println("* Event calendar by event date and start time *");
            Event[] sortedDate=events.clone();
            for (int i = 0; i < numEvents; i++) { //sorting the events array by date using selection sort
                Event earliestEvent = sortedDate[i];
                int placeToSwitch = i;
                for (int p = i + 1; p < numEvents; p++) {
                    if (sortedDate[p].compareTo(earliestEvent) < 0) {
                        earliestEvent = sortedDate[p];
                        placeToSwitch = p;
                    }
                }
                Event temp = sortedDate[placeToSwitch];
                sortedDate[placeToSwitch] = sortedDate[i];
                sortedDate[i] = temp;
            } // events is now sorted
            for (int i = 0; i < numEvents; i++) {
                System.out.println(sortedDate[i].toString());
            }
            System.out.println("* end of event calendar *");
        }
    }

    public void printByCampus() {
        if (numEvents == 0) {
            System.out.println("Event calender is empty!");
        } else {
            System.out.println("* Event calendar by campus and building *");
            Event[] sortedEvents = sortByCampus(events); //making a new array to store the events sorted by campus in alphabetical order
            // sortedEvents is now sorted by campus alphabetically
            for (int i = 0; i < numEvents; i++) {
                System.out.println(sortedEvents[i].toString());
            }
            System.out.println("* end of event calendar *");
        }
    } //ordered by campus and building/room


    public void printByDepartment() {

        if (numEvents == 0) {
            System.out.println("Event calender is empty!");
        } else {
            System.out.println("* Event calendar by department *");
            Event[] sortedEvents = sortByDepartment(events); //making a new array to store the events sorted by campus in alphabetical order
            for (int i = 0; i < sortedEvents.length; i++) {
                System.out.println(sortedEvents[i].toString());
            }
            System.out.println("* end of event calendar *");

        } //ordered by department

    }

    public Event[] sortByCampus(Event[] array){
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
    }
    public Event[] sortByDepartment(Event[] array){
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
    }



    }
