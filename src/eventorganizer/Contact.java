package eventorganizer;

/**
Contact object holding a department and email value featuring methods to validate the object and to get the instance variables
Then, additional lines of description to elaborate the details if necessary.
@author David Rahabi, Judah Farkas
*/
public class Contact {
    private Department department;
    private String email;
//test
    /**
     * EDIT
    Check if the contact object is valid by checking if the email is in the correct format and if the department value is correct
    Does nothing if the student is not in the list.
    @param student the student to be removed.
    @return true if the student was successfully removed, false otherwise.
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
    

    Contact(Department department, String email){
        this.department=department;
        this.email=email;
    }

    public Department getDepartment(){
        return this.department;
    }
    public String getEmail(){
        return this.email;
    }


    }

