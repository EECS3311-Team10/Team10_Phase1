import java.util.Scanner;

public class ConsultancyApp {
    private Scanner scanner = new Scanner(System.in);
    // Assume you have lists of your users and services here

    public void start() {
        System.out.println("Welcome to the Consultancy System");
        System.out.println("1. Login\n2. Exit");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            handleLogin();
        
        } 
        else {
            System.out.println("Exiting...");
            System.exit(0);
        }

        while (true) { 
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if(choice == 1) {
                // Browse Services
            }
            else if(choice == 2) {
                // View Booking History
            }
            else if(choice == 3) {
                // Manage Payment Methods
            }
            else if(choice == 4) {
                // Cancel Booking
            }
            else if(choice == 5) {
                // Request Booking
            }
            else if(choice == 6) {
                // Process Payment
            }
            else if(choice == 7) {
                // View Payment History
            }
            if(choice == 8) {
                confirmLogout();
            }
            else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }



    private void handleLogin() {
        System.out.print("Enter your User ID (e.g., CL-1): ");
        String id = scanner.nextLine();
        // Logic to find user by ID and check their type
        System.out.println("Logged in as: " + id);
        showClientMenu();
    }

    private void confirmLogout() {
        System.out.println("Are you sure you want to logout? (Y/N)");
        String choice = scanner.nextLine();
        if(choice.equalsIgnoreCase("Y")) {
            System.out.println("Logging out...");
            start(); // Return to main menu
        } else {
            System.out.println("Logout cancelled.");
            showClientMenu(); // Return to client menu
        }
    }

    private void showClientMenu() {
        System.out.println("\n--- Client Menu ---");
        System.out.println("1. Browse Services\n2. View Booking History\n3. Manage Payment Methods\n4. Cancel Booking\n5. Request Booking\n6. Process Payment\n7. View Payment History\n8. Logout\n");
        // Implement logic for selecting times here
    }
}