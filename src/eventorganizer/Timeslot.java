package eventorganizer;

import java.io.InterruptedIOException;

/**
 * The Timeslot enumb class is a class that takes in the inputted Timeslot and stores the associated String time with it
 * The class consists of 4 methods
 * Timeslot()
 * Constructor for timeslot
 * getTime()
 * Getter method of time
 * compareTime()
 * compares the inputted times
 * getEndTime()
 * gets the end time of the event
 */
public enum Timeslot {
    MORNING("10:30am"),
    AFTERNOON("2:00pm"),
    EVENING("6:30pm");

    private final String time;

    /**
     * Constructor for time
     *
     * @param time
     */
    Timeslot(String time) {
        this.time = time;
    }

    /**
     * Getter method for time
     *
     * @return this.time
     */
    public String getTime() {
        return time;
    } //returns time

    /**
     * Compares 2 times
     *
     * @param timeslot timeslot
     * @return 1 if time is later than, -1 if time is before, 0 if time is =
     */
    public int compareTime(Timeslot timeslot) {

        String[] thisTime = this.time.split(":");
        String thisamOrpm = thisTime[1].substring(2, thisTime[1].length());
        thisTime[1] = thisTime[1].substring(0, thisTime[1].length() - 2);
        int thisHour = Integer.parseInt(thisTime[0]);
        int thisMinute = Integer.parseInt(thisTime[1]);
        if (thisamOrpm.equals("pm")) thisHour += 12;

        String[] otherTime = timeslot.time.split(":");
        String otheramOrpm = otherTime[1].substring(2, otherTime[1].length());
        otherTime[1] = otherTime[1].substring(0, otherTime[1].length() - 2);
        int otherHour = Integer.parseInt(otherTime[0]);
        int otherMinute = Integer.parseInt(otherTime[1]);
        if (otheramOrpm.equals("pm")) otherHour += 12;

        if (thisHour > otherHour) return 1;
        else if (otherHour > thisHour) return -1;
        else {
            if (thisMinute > otherMinute) return 1;
            else if (otherMinute > thisMinute) return -1;
            else return 0;
        }
    }

    /**
     * Annalizes and returns the end time of the timeslot
     *
     * @param duration duration
     * @return hours
     */
    public String getEndTime(int duration) {
        String[] makeTimeArray = time.split(":");

        String amOrpm = makeTimeArray[1].substring(2, makeTimeArray[1].length());
        makeTimeArray[1] = makeTimeArray[1].substring(0, makeTimeArray[1].length() - 2);
        int hours = Integer.parseInt(makeTimeArray[0]);
        int minutes = Integer.parseInt(makeTimeArray[1]);
        int beforeHour = 60 - minutes;
        if (duration < beforeHour) {
            minutes += duration;
            if (minutes < 10) {
                return hours + ":0" + minutes + amOrpm;
            }
            return hours + ":" + minutes + amOrpm;
        } else {
            int hourToAdd = duration / 60;
            int minutesToAdd = duration - (60 * hourToAdd);
            hours += hourToAdd;
            minutes += minutesToAdd;
            if (minutes >= 60) {

                hours++;
                minutes -= 60;
            }
        }
        if (hours == 12) {
            amOrpm = "pm";
        }
        if (minutes < 10) {
            return hours + ":0" + minutes + amOrpm;
        }
        return hours + ":" + minutes + amOrpm;
    }
}

