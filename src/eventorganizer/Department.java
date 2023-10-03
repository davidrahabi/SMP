package eventorganizer;

/**
 * The Department enumb class gives values to the input using enumb
 * in this class there is 3 methods
 * Department();
 * constructor for the department class
 * getDepartmentName();
 * getter method for department
 * isValid()
 * checks if the department is a valid input
 *
 * @author David Rahabi, Judah Farkas
 */

public enum Department {
    BAIT("Business Analytics and Information Technology"),
    CS("Computer Science"),
    EE("Electrical Engineering"),
    ITI("Information Technology and Informatics"),
    MATH("Mathematics");


    private final String DEPARTMENTNAME;

    /**
     * constructor for department
     *
     * @param department sets department
     */
    Department(String department) {
        this.DEPARTMENTNAME = department;
    }

    /**
     * Getter method for department
     *
     * @return DEPARTMENTNAME department name
     */
    public String getDepartmentName() {
        return this.DEPARTMENTNAME;
    }

    /**
     * Checks if the department is a valid input
     *
     * @return true if valid, otherwise false
     */
    public boolean isValid() {
        for (Department department : Department.values()) {
            if (department.DEPARTMENTNAME.equals(this.DEPARTMENTNAME)) return true;
        }
        return false;

    }
}
