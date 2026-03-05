import Users.*;

public class Main {
    public static void main(String[] args) {
        // 1. Create a Registry to hold our users
        UserRegistry registry = new UserRegistry();

        // 2. Pre-load ("Seed") the users
        // These will automatically get IDs: CL-1, CO-1, and AD-1
        Client defaultClient = new Client("John Doe", "john@email.com", "555-0001");
        Consultant defaultConsultant = new Consultant("Jane Smith", "jane@email.com", "555-0002");
        Admin defaultAdmin = new Admin("System Admin", "admin@firm.com", "555-9999");

        // 3. Add them to the registry
        registry.addUser(defaultClient);
        registry.addUser(defaultConsultant);
        registry.addUser(defaultAdmin);

        // 4. Start the CLI and pass it the registry so it can "find" these users
        ConsultancyApp app = new ConsultancyApp();
        app.start();
    }
}
