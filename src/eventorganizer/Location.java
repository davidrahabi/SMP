package eventorganizer;

/**
 * The Location enumb class gives values to the input using enumb
 * in this class there is 3 methods
 * Location();
 * constructor for the location class
 * getBuildingName();
 * getter method for building name
 * getCampus()
 * getter method for campus
 *
 * @author David Rahabi, Judah Farkas
 */
public enum Location {
    ARC103("Allison Road Classroom", "Busch"),
    HLL114("Hill Center", "Busch"),
    AB2225("Academic Building", "College Avenue"),
    MU302("Murray Hall", "College Avenue"),
    BE_AUD("Beck Hall", "Livingston"),
    TIL232("Tillett Hall", "Livingston");


    private final String BUILDINGNAME;
    private final String CAMPUS;

    /**
     * Constructor for location
     *
     * @param building building name
     * @param campus   campus name
     */
    Location(String building, String campus) {
        this.BUILDINGNAME = building;
        this.CAMPUS = campus;
    }

    /**
     * getter method for building name
     *
     * @return this.BUILDINGNAME
     */
    public String getBuildingName() {
        return this.BUILDINGNAME;
    }

    /**
     * getter method for campus
     *
     * @return this.CAMPUS
     */
    public String getCampus() {
        return this.CAMPUS;
    }


}
