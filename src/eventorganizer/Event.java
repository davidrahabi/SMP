package eventorganizer;
public class Event implements Comparable<Event> {
    private Date date; //the event date
    private Timeslot startTime; //the starting time
    private Location location;
    private Contact contact; //include the department name and email
    private int duration; //in minutes



    @Override
    public boolean equals(Object obj){
        if(obj instanceof Event){
            Event event=(Event) obj;
            return event.date.equals(this.date) && event.startTime.equals(this.startTime) && event.location.equals(this.location); 
    
        }
        return false;
    }

    @Override
    public String toString(){
        return "[Event Date: " + date.getMonth() + "/" + date.getDay() + "/" + date.getYear() + "] [Start: " +
            startTime.getTime()+"] [End: " + startTime.getEndTime(duration) + "]  @" + location.name() + " ("
            + location.getBuildingName() +"," + location.getCampus()+") [Contact: " + 
            contact.getDepartment().getDepartmentName() + ", " + contact.getEmail()+"]";

        // [Event Date: 10/21/2023] [Start: 2:00pm] [End: 3:00pm] @HLL114 (Hill Center, Busch) [Contact: Computer Science, cs@rutgers.edu]
    }


    @Override
    public int compareTo(Event event){
        if(this.date.compareTo(event.date)==1) return 1;
        else if(this.date.compareTo(event.date)==-1) return -1;
        else{
            if(this.startTime.compareTime(event.startTime)==1) return 1;
            else if(this.startTime.compareTime(event.startTime)==-1) return -1;
            else return 0;
        }
    }




    Event(Date date, Timeslot startTime, Location location, Contact contact, int duration){
        this.date=date;
        this.startTime=startTime;
        this.location=location;
        this.contact=contact;
        this.duration=duration;
    }

    Event(Date date, Timeslot startTime, Location location){
        this.date=date;
        this.startTime=startTime;
        this.location=location;
    }

    

    public Contact getContact(){
        return this.contact;
    }
    public Date getDate(){
        return this.date;
    }
    public Timeslot getTimeslot(){
        return this.startTime;
    }
    public Location getLocation(){
        return this.location;
    }
    public int getDuration(){
        return this.duration;
    }

}

