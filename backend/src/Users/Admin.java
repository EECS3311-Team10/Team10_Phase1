package Users;

public class Admin extends User {
    
    private static int idCounter = 1; 

    public Admin(String name, String email, String phone) {
        super(name, email, phone, "Admin"); 
        this.setRole("Admin");
        this.userId = "AD-" + idCounter++;
    }

    public boolean reviewConsultantRequest(){
        return true;
    }

 
    public void defineSystemPolicies(){
        //placeholder for defining system policies
        System.out.println("System policies defined.");
    }

}
