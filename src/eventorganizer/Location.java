package eventorganizer;
public enum Location{

    HLL114("Hill Center", "Busch"),
    ARC103("Allison Road Classroom", "Busch"),
    BE_AUD("Beck Hall", "Livingston"),
    TIL232("Tillett Hall", "Livingston"),
    AB2225("Academic Building", "College Avenue"),
    MU302("Murray Hall", "College Avenue");

    private final String BUILDINGNAME;
    private final String CAMPUS;

    Location(String building, String campus){
        this.BUILDINGNAME=building;
        this.CAMPUS=campus;
    }

    public String getBuildingName(){
        return this.BUILDINGNAME;
    }
    public String getCampus(){
        return this.CAMPUS;
    }


}
