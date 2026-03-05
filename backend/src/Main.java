import Users.*;

public class Main {
    public static void main(String[] args) {
        // Create two Clients
        Client c1 = new Client("Alice", "alice@email.com", "555-0101");
        Client c2 = new Client("Bob", "bob@email.com", "555-0102");

        // Create one Consultant
        Consultant co1 = new Consultant("Charlie", "charlie@email.com", "555-0201");

        // Create one Admin
        // (Assuming you followed the same pattern for Admin)
        Admin a1 = new Admin("Dana", "dana@email.com", "555-0301");

        System.out.println("--- User ID Verification ---");
        System.out.println("Client 1: " + c1.getUserID()); // Should be CL-1
        System.out.println("Client 2: " + c2.getUserID()); // Should be CL-2
        System.out.println("Consultant 1: " + co1.getUserID()); // Should be CO-1
        System.out.println("Admin 1: " + a1.getUserID()); // Should be AD-1

        // Verify getters from parent User class
        System.out.println("\n--- Data Verification ---");
        System.out.println("Name for " + c1.getUserID() + ": " + c1.getName());
    }
}
