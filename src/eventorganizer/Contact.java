package eventorganizer;

/**
 * The Contact class holds the department and email of an event. This class also checks if the email is a valid email
 * This class has 4 methods:
 * isValid(); :
 * the isValid function main goal is to validate the email.
 * It needs to ensure that the proper input is [USER]@rutgers.edu
 * Contact() :
 * Setter methods for contact
 * getDepartment();
 * Getter method for department
 * getEmail();
 * getter method for email
 * @author David Rahabi, Judah Farkas
*/


public class Contact {
    private Department department;
    private String email;
    /**
     * isValid checks if the email is a valid email address if so
     * @return true if valid email, false if not
    */

    public boolean isValid(){  //complete this 
        if (this.email == null){
            return false;
        }


        int amountOfAts=0;
        for(int i=0;i<email.length();i++){
            if(email.charAt(i)=='@'){
                if(i==0 || i==email.length()-1){
                    return false;
                }
                amountOfAts++;
            }
        }
        if(amountOfAts>1 || amountOfAts==0) return false;
        else{
            String[] emailSplit= email.split("@");
            if(!emailSplit[1].equals("rutgers.edu")) return false;
        }

        return this.department.isValid();
    }

    /**
     * sets email and department to contact
     * @param department the department input
     * @param email the email string
     */
    Contact(Department department, String email){
        this.department=department;
        this.email=email;
    }

    /**
     * getter method for department
     * @return this.department
     */
    public Department getDepartment(){
        return this.department;
    }

    /**
     * getter method for email
     * @return this.email
     */
    public String getEmail(){
        return this.email;
    }


    }

