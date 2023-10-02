package eventorganizer;
public enum Department {
BAIT("Business Analytics and Information Technology"),
CS("Computer Science"), 
EE("Electrical Engineering"),
ITI("Information Technology and Informatics"), 
MATH("Mathematics");


private final String DEPARTMENTNAME;

Department(String department){
     this.DEPARTMENTNAME=department;
}


public String getDepartmentName(){
     return this.DEPARTMENTNAME;
}

public boolean isValid(){
     for(Department department : Department.values()){
          if(department.DEPARTMENTNAME.equals(this.DEPARTMENTNAME)) return true;
     }
     return false;

}

    /**
     * Locations:
     * HLL114, Hill Center, Busch
ARC103, Allison Road Classroom, Busch
BE_AUD, Beck Hall, Livingston
TIL232, Tillett Hall, Livingston
AB2225, Academic Building, College Avenue
MU302, Murray Hall, College Avenue
     */
    
}
