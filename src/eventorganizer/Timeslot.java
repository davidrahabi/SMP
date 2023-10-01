package eventorganizer;
public enum Timeslot{
    MORNING("10:30am"),
    AFTERNOON("2:00pm"),
    EVENING("6:30pm");

    private final String time;

    Timeslot(String time){
        this.time=time;
    }

    public String getTime(){
        return time;
    }

    public int compareTime(Timeslot timeslot){

        String[] thisTime = this.time.split(":");
        String thisamOrpm = thisTime[1].substring(2, thisTime[1].length());
        thisTime[1] = thisTime[1].substring(0, thisTime[1].length() - 2);
        Integer thisH = Integer.valueOf(thisTime[0]); //converting string object to Integer Object (cannot convert string directly to int)
        int thisHour = thisH.intValue();
        Integer thisM = Integer.valueOf(thisTime[1]); //converting string object to Integer Object (cannot convert string directly to int)
        int thisMinute = thisM.intValue();
        if(thisamOrpm.equals("pm")) thisHour+=12;




        String[] otherTime = timeslot.time.split(":");
        String otheramOrpm = otherTime[1].substring(2, otherTime[1].length());
        otherTime[1] = otherTime[1].substring(0, otherTime[1].length() - 2);
        Integer otherH = Integer.valueOf(otherTime[0]); //converting string object to Integer Object (cannot convert string directly to int)
        int otherHour = otherH.intValue();
        Integer otherM = Integer.valueOf(otherTime[1]); //converting string object to Integer Object (cannot convert string directly to int)
        int otherMinute = otherM.intValue();
        if(otheramOrpm.equals("pm")) otherHour += 12;



        if (thisHour>otherHour) return 1;
        else if (otherHour>thisHour) return -1;
        else{
            if (thisMinute>otherMinute) return 1;
            else if (otherMinute>thisMinute) return -1;
            else return 0;
        }
    }


    public String getEndTime(int duration){
       String[] s = time.split(":"); //rename this variable

        //I want to remove the AM/PM from the string, but first i need to save it for later use (the final string needs to include the am/pm)
        String amOrpm = s[1].substring(2, s[1].length());

        s[1] = s[1].substring(0, s[1].length() - 2); //this will remove the "am" or "pm" from the string so now we 
        //are working with two strings that can be easily converted to integers

        Integer h= Integer.valueOf(s[0]); //converting string object to Integer Object (cannot convert string directly to int)
        int hours = h.intValue(); //extracting the int value from the Integer Object

        //do same thing with minutes

        Integer m = Integer.valueOf(s[1]);
        int minutes = m.intValue();

        //now we can use the duration time to calculate when the endTime of the Timeslot is

        int beforeHour=60-minutes; //get how many minutes it is til the next hour

        if (duration<beforeHour){ //in this case, we can just add the time to minutes and return bc hours will not change
            minutes += duration;
            if (minutes < 10){
                return hours + ":0" + minutes+amOrpm;
                // in the case that minutes is lets say the hoours is 10 and minutes is 5, the 
                //time would get printed as 10:5pm, need to add zero before so it becomes 10:05pm
            }
            return hours + ":" + minutes+amOrpm;
        }
       
        else{ //now this is the more complicated case where both hours and minutes will have to be updated accordingly
            

            //first, its easier to calculate how many hours need to be added then do the minutes after

            int hourToAdd = duration/60; //this will be integer division because they are both ints,
                                        //so this will give the exact hours ex: 130/60=2

            int minutesToAdd = duration-(60 * hourToAdd); //this will give the minutes
            /**
             * ex: 130 is 2 hours and 10 minutes
             * 130/60=2 like above for hourToAdd
             * 130-(60*2)=10
             * 
             * this also works if the its an exact amount of hours
             * 
             */
             hours += hourToAdd;
             
             minutes+=minutesToAdd; //new hr and min values
             if (minutes >= 60){
                
                hours++;
                minutes -= 60;
                
                 /*
                  * in the case that minutes goes above 60, another hour will be added
                  ex:hours=5 minutes=75 (time would be 6:15)
                  hours++ = 6
                  75-60=15

                  */
             }
        }
        if(hours == 12){
            amOrpm = "pm"; //the only case where the am/pm will change
        }

        if (minutes < 10){
            return hours + ":0"+minutes + amOrpm; //if hrs was 10 and minutes was 3, the return value would be 10:3am, need to add 0 so it becomes 10:03am
        }

        return hours + ":" + minutes + amOrpm;
    }
}

