package Users;

public class Admin extends User {
    
    private static int idCounter = 1; 

    public Admin(){
        super(); 
        this.setRole("Admin");
        this.userId = "AD-" + idCounter;
        incrementCounter();
    }

    public boolean reviewConsultantRequest(){
        return true;
    }

   public static void incrementCounter(){
        idCounter++;
    }
 
    public void defineSystemPolicies(){

    }

    public String generateReports(){
        return null;
    }

    
}
