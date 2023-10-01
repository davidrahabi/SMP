package eventorganizer;
public class Date implements Comparable<Date> { // add comparable method
    private int year;
    private int month;
    private int day;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    
    public boolean isValid() { // check if the date is a valid calendar date
        if (this.year > 1900) {
            switch (this.month) {
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                    if (this.day >= 1 && this.day <= 31) {
                        return true;
                    }
                    break;
                case 4: case 6: case 9: case 11:
                    if (this.day >= 1 && this.day <= 30) {
                        return true;
                    }
                    break;
                case 2:
                    if (this.year % QUADRENNIAL == 0) {
                        if (this.year % CENTENNIAL == 0) {
                            if (this.year % QUATERCENTENNIAL == 0) {
                                if (this.day <= 28 && this.day >= 1) {
                                    return true;
                                }
                                break;
                            } else if (this.day <= 29 && this.day >= 1){
                                return true;
                            }
                            break;
                        } else if (this.day <= 29 && this.day >= 1){
                            return true;
                        }
                        break;
                    } else if (this.day <= 28 && this.day >= 1){
                        return true;
                    }
                    break;
                default:
                    return false;
            }
        }
        return false;
    }

    Date(int month, int day, int year){
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            Date date = (Date) obj;

            return date.year == this.year && date.month == this.month && date.day == this.day;

        }
        return false;
    }

    @Override
    public int compareTo(Date date) {
        if (this.year > date.year)
            return 1; // compare years first
        else if (date.year > this.year)
            return -1;
        else {// if they are equal
            if (this.month > date.month)
                return 1; // compare months
            else if (date.month > this.month)
                return -1;
            else { // if months are equal
                if (this.day > date.day)
                    return 1; // compare days
                else if (date.day > this.day)
                    return -1;
                else
                    return 0; // if days are equal
            }
        }
        /*
         * NEED TO CHECK IS THIS IS OK TO DO/ THIS IS HOW WE ARE SUPPOSED TO DO THIS
         */
    }
    public static  void main(String[] args){
        testDaysInFeb_NonLeap();
        testDaysInFeb_Leap();
    }

    /** Test Case #1 */
    private static void testDaysInFeb_NonLeap(){
        Date date = new Date(2, 29, 2011);
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #1: # of days in Feb. in a non-leap year is 28");
        testResult(date, expectedOutput, actualOutput);
    }
    /** Test Case 2*/
    public static void testDaysInFeb_Leap(){
        Date date = new Date(2, 29, 2012);
        boolean expectedOutput = true;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #2: # of days in Feb. in a non-leap year is 29");
        testResult(date, expectedOutput, actualOutput);
    }



    public static void testResult(Date date, boolean expectedOutput, boolean actualOutput){
        if (expectedOutput == actualOutput){
            System.out.println(true);
        }
        else {
            System.out.println(false);
        }
    }
}
