
import Users.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import payment.PaymentMethod;
import service.*;

// - Client has: requestBooking(Booking), cancelBooking(Booking), viewBookingHistory(), getPaymentMethods(), addPaymentMethod(...)
// - Client has: processPayment(Payment, PaymentStrategy) and viewPaymentHistory()
// - Booking has: getBookingID(), getScheduledTime(), getPrice() + state actions (confirm/reject/cancel/processPayment/complete) if you want to use them
// If method names differ, just rename the calls inside the menu handlers.
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // -----------------------------
        // Create services
        // -----------------------------
        List<Service> services = new ArrayList<>();
        services.add(new Service("Consulting", "Intro session", 100.0));
        services.add(new Service("Resume Review", "30-min review", 60.0));
        services.add(new Service("Mock Interview", "45-min interview", 120.0));

        // -----------------------------
        // Create users
        // -----------------------------
        List<User> users = new ArrayList<>();

        Client client = new Client("John Doe", "john@email.com", "555-0001");
        Consultant consultant = new Consultant("Jane Smith", "jane@email.com", "555-0002");
        Admin admin = new Admin("System Admin", "admin@firm.com", "555-9999");

        users.add(client);
        users.add(consultant);
        users.add(admin);

        while (true) {

            System.out.println("\nWelcome to the Consultancy System");
            System.out.println("1. Login");
            System.out.println("2. Exit");

            int choice = readInt(scanner);

            if (choice == 1) {

                System.out.print("Enter your User ID (e.g., CL-1): ");
                String id = scanner.nextLine().trim();

                User currentUser = null;

                // find user
                for (User u : users) {
                    if (u.getUserID().equalsIgnoreCase(id)) {
                        currentUser = u;
                        break;
                    }
                }

                if (currentUser == null) {
                    System.out.println("User not found.");
                    continue;
                }

                System.out.println("Logged in as: " + currentUser.getName());

                // determine menu by type
                if (currentUser instanceof Client) {
                    runClientMenu(scanner, (Client) currentUser, services, (Consultant) consultant);
                } else if (currentUser instanceof Consultant) {
                    runConsultantMenu(scanner, (Consultant) currentUser);
                } else if (currentUser instanceof Admin) {
                    runAdminMenu(scanner, (Admin) currentUser);
                }

            } else {
                System.out.println("Exiting...");
                break;
            }
        }

        scanner.close();
    }

    // =========================================================
    // Client Menu (✅ “finish all the booking choices”)
    // =========================================================
    public static void runClientMenu(Scanner scanner,
            Client client,
            List<Service> services,
            Consultant consultant) {

        while (true) {

            showClientMenu();
            int choice = readInt(scanner);

            if (choice == 1) {
                browseServices(services);
            } else if (choice == 2) {
                // View Booking History
                System.out.println("\n--- Booking History ---");
                List<?> bookings = client.viewBookingHistory();
                if (bookings == null || bookings.isEmpty()) {
                    System.out.println("No bookings yet.");
                } else {
                    for (Object b : bookings) {
                        System.out.println(b);
                    }
                }
            } else if (choice == 3) {
                // Manage Payment Methods (add/list/set default)
                managePaymentMethods(scanner, client);
            } else if (choice == 4) {
                // Cancel Booking
                cancelBookingFlow(scanner, client);
            } else if (choice == 5) {
                // Request Booking
                requestBookingFlow(scanner, client, services, consultant);
            } else if (choice == 6) {
                // Process Payment
                processPaymentFlow(scanner, client);
            } else if (choice == 7) {
                // View Payment History
                System.out.println("\n--- Payment History ---");
                List<?> payments = client.viewPaymentHistory();
                if (payments == null || payments.isEmpty()) {
                    System.out.println("No payments yet.");
                } else {
                    for (Object p : payments) {
                        System.out.println(p);
                    }
                }
            } else if (choice == 8) {

                System.out.println("Are you sure you want to logout? (Y/N)");
                String logoutChoice = scanner.nextLine().trim();

                if (logoutChoice.equalsIgnoreCase("Y")) {
                    System.out.println("Logging out...");
                    break;
                }
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    public static void showClientMenu() {
        System.out.println("\n--- Client Menu ---");
        System.out.println("1. Browse Services");
        System.out.println("2. View Booking History");
        System.out.println("3. Manage Payment Methods");
        System.out.println("4. Cancel Booking");
        System.out.println("5. Request Booking");
        System.out.println("6. Process Payment");
        System.out.println("7. View Payment History");
        System.out.println("8. Logout");
        System.out.print("Choice: ");
    }

    // ---------------------------------------------------------
    // UC5 Request Booking
    // ---------------------------------------------------------
    private static void requestBookingFlow(Scanner scanner,
            Client client,
            List<Service> services,
            Consultant consultant) {

        System.out.println("\n--- Request a Booking ---");
        browseServices(services);

        System.out.print("Pick a service number: ");
        int idx = readInt(scanner) - 1;
        if (idx < 0 || idx >= services.size()) {
            System.out.println("Invalid service.");
            return;
        }
        Service selected = services.get(idx);

        System.out.print("Enter scheduled time in hours from now (e.g., 24): ");
        int hoursFromNow = readInt(scanner);
        LocalDateTime requestedTime = LocalDateTime.now();
        LocalDateTime scheduledTime = requestedTime.plusHours(hoursFromNow);

        // bookingID: simple local generator
        String bookingID = "BK-" + System.currentTimeMillis();

        // ✅ Create booking (your Booking constructor may differ—adjust if needed)
        // If your Booking expects CancellationPolicy too, pass one from PolicyManager etc.
        bookingstates.Booking booking = new bookingstates.Booking(
                bookingID, requestedTime, scheduledTime, selected.getPrice()
        );

        // put into client history
        client.requestBooking(booking);

        // Optional: also notify consultant / add to their queue if you have that
        // consultant.receiveBookingRequest(booking); // rename if you have it
        System.out.println("Booking requested successfully:");
        System.out.println(booking);
    }

    // ---------------------------------------------------------
    // UC3 Cancel Booking
    // ---------------------------------------------------------
    private static void cancelBookingFlow(Scanner scanner, Client client) {

        System.out.println("\n--- Cancel a Booking ---");

        List<?> bookings = client.viewBookingHistory();
        if (bookings == null || bookings.isEmpty()) {
            System.out.println("No bookings to cancel.");
            return;
        }

        // print with index
        for (int i = 0; i < bookings.size(); i++) {
            System.out.println((i + 1) + ") " + bookings.get(i));
        }

        System.out.print("Choose booking number to cancel: ");
        int pick = readInt(scanner) - 1;
        if (pick < 0 || pick >= bookings.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Object obj = bookings.get(pick);

        // If your list is List<Booking>, just cast directly
        bookingstates.Booking booking = (bookingstates.Booking) obj;

        // call client method
        client.cancelBooking(booking);

        // If your Booking uses state pattern and needs booking.cancel():
        // booking.cancel();
        System.out.println("Cancelled: " + booking.getBookingID());
    }

    // ---------------------------------------------------------
    // UC6 Manage Payment Methods
    // ---------------------------------------------------------
    private static void managePaymentMethods(Scanner scanner, Client client) {

        while (true) {
            System.out.println("\n--- Manage Payment Methods ---");
            System.out.println("1) List my methods");
            System.out.println("2) Add Debit Card");
            System.out.println("3) Add Credit Card");
            System.out.println("4) Add PayPal");
            System.out.println("5) Add Bank Transfer");
            System.out.println("6) Back");
            System.out.print("Choice: ");

            int choice = readInt(scanner);

            if (choice == 1) {
                List<PaymentMethod> methods = client.getPaymentMethods();
                if (methods == null || methods.isEmpty()) {
                    System.out.println("No payment methods.");
                } else {
                    System.out.println("--- Your Payment Methods ---");
                    for (PaymentMethod m : methods) {
                        m.getPaymentDetails(); // or override toString in your PaymentMethod classes
                    }
                }
            } else if (choice == 2) {
                System.out.print("Card number: ");
                String cardNo = scanner.nextLine().trim();
                System.out.print("Card holder name: ");
                String holder = scanner.nextLine().trim();
                System.out.print("Expiry (MM/YY): ");
                String exp = scanner.nextLine().trim();
                System.out.print("CVV: ");
                String cvv = scanner.nextLine().trim();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");

                YearMonth expiryDate;
                expiryDate = YearMonth.parse(exp, formatter);

                //payment.PaymentMethod pm = new payment.DebitCard(cardNo, holder, expiryDate, cvv);
                client.addPaymentMethod("DEBIT", cardNo, expiryDate, cvv, holder);
                //System.out.println("Debit card added.");
            } else if (choice == 3) {
                System.out.print("Card number: ");
                String cardNo = scanner.nextLine().trim();
                System.out.print("Card holder name: ");
                String holder = scanner.nextLine().trim();
                System.out.print("Expiry (MM/YY): ");
                String exp = scanner.nextLine().trim();
                System.out.print("CVV: ");
                String cvv = scanner.nextLine().trim();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");

                YearMonth expiryDate;
                expiryDate = YearMonth.parse(exp, formatter);
                //payment.PaymentMethod pm = new payment.CreditCard(cardNo, holder, expiryDate, cvv);
                client.addPaymentMethod("CREDIT", cardNo, expiryDate, cvv, holder);
                //System.out.println("Credit card added.");
            } else if (choice == 4) {
                System.out.print("PayPal email: ");
                String email = scanner.nextLine().trim();

                //payment.PaymentMethod pm = new payment.PayPal(email);
                client.addPaymentMethod(email);
                //System.out.println("PayPal added.");
            } else if (choice == 5) {
                System.out.print("Account name: ");
                String name = scanner.nextLine().trim();
                System.out.print("Address: ");
                String address = scanner.nextLine().trim();
                System.out.print("Account number: ");
                String accountNo = scanner.nextLine().trim();
                System.out.print("Routing number: ");
                String routingNo = scanner.nextLine().trim();

                //payment.PaymentMethod pm = new payment.BankTransfer(accountNo, address, name, routingNo);
                client.addPaymentMethod(accountNo, address, name, routingNo);
                //System.out.println("Bank transfer added.");
            } else if (choice == 6) {
                return;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    // ---------------------------------------------------------
    // UC5 Process Payment (booking -> create payment -> strategy)
    // ---------------------------------------------------------
    private static void processPaymentFlow(Scanner scanner, Client client) {

        System.out.println("\n--- Process Payment ---");

        List<?> bookings = client.viewBookingHistory();
        if (bookings == null || bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        // select booking
        for (int i = 0; i < bookings.size(); i++) {
            System.out.println((i + 1) + ") " + bookings.get(i));
        }
        System.out.print("Choose booking number to pay for: ");
        int pick = readInt(scanner) - 1;
        if (pick < 0 || pick >= bookings.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        bookingstates.Booking booking = (bookingstates.Booking) bookings.get(pick);

        // choose payment strategy
        payment.PaymentMethod pm = choosePaymentMethod(scanner, client);

        payment.PaymentStrategy strategy;

        if (pm instanceof payment.CreditCard) {
            strategy = new payment.CreditCardStrategy((payment.CreditCard) pm);
        }
        else if (pm instanceof payment.DebitCard) {
            strategy = new payment.DebitCardStrategy((payment.DebitCard) pm);
        }
        else if (pm instanceof payment.PayPal) {
            strategy = new payment.PayPalStrategy((payment.PayPal) pm);
        }
        else if (pm instanceof payment.BankTransfer) {
            strategy = new payment.BankTransferStrategy((payment.BankTransfer) pm);
        }
        else {
            System.out.println("Invalid payment method.");
            return;
        }// Create Payment object (adjust constructor to your Payment.java)
        String paymentID = "PM-" + System.currentTimeMillis();
        String txID = "TX-" + UUID.randomUUID();

        // If you use enum PaymentStatus, pick an initial value:
        payment.PaymentStatus status = payment.PaymentStatus.PENDING;

        payment.Payment payment = new payment.Payment(
                paymentID,
                booking.getPrice(),
                status,
                java.time.LocalDateTime.now(),
                txID
        );

        // run client’s payment flow
        client.processPayment(payment, strategy);

        // if your booking state machine should advance:
        // booking.processPayment();
        client.addPayment(payment);
        System.out.println("Payment processed for booking: " + booking.getBookingID());

    }

    private static payment.PaymentMethod choosePaymentMethod(Scanner scanner, Users.Client client) {
        System.out.println("\nChoose payment method to use/add:");
        System.out.println("1) Credit Card");
        System.out.println("2) Debit Card");
        System.out.println("3) PayPal");
        System.out.println("4) Bank Transfer");
        System.out.print("Choice: ");

        int methodChoice = readInt(scanner);   // use your existing readInt

        // Create + return the correct subclass (so your casts work)
        switch (methodChoice) {
            case 1 -> {
                System.out.print("Card number: ");
                String cardNo = scanner.nextLine().trim();
                System.out.print("Card holder name: ");
                String holder = scanner.nextLine().trim();
                System.out.print("Expiry (MM/YY): ");
                String exp = scanner.nextLine().trim();
                System.out.print("CVV: ");
                String cvv = scanner.nextLine().trim();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");

                YearMonth expiryDate;
                expiryDate = YearMonth.parse(exp, formatter);

                //payment.CreditCard cc = new payment.CreditCard(cardNo, holder, expiryDate, cvv);
                client.addPaymentMethod("CREDIT", cardNo, expiryDate, cvv, holder);
                return client.getPaymentMethods().get(0);
            }
            case 2 -> {
                System.out.print("Card number: ");
                String cardNo = scanner.nextLine().trim();
                System.out.print("Card holder name: ");
                String holder = scanner.nextLine().trim();
                System.out.print("Expiry (MM/YY): ");
                String exp = scanner.nextLine().trim();
                System.out.print("CVV: ");
                String cvv = scanner.nextLine().trim();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");

                YearMonth expiryDate;
                expiryDate = YearMonth.parse(exp, formatter);

                //payment.DebitCard dc = new payment.DebitCard(cardNo, holder, expiryDate, cvv);
                client.addPaymentMethod("DEBIT", cardNo, expiryDate, cvv, holder);
                return client.getPaymentMethods().get(0);
            }
            case 3 -> {
                System.out.print("PayPal email: ");
                String email = scanner.nextLine().trim();

                //payment.PayPal pp = new payment.PayPal(email);
                client.addPaymentMethod(email);
                return client.getPaymentMethods().get(0);
            }
            case 4 -> {
                System.out.print("Account name: ");
                String name = scanner.nextLine().trim();
                System.out.print("Address: ");
                String addr = scanner.nextLine().trim();
                System.out.print("Account number: ");
                String acct = scanner.nextLine().trim();
                System.out.print("Routing number: ");
                String routing = scanner.nextLine().trim();

                //payment.BankTransfer bt = new payment.BankTransfer(acct, addr, name , routing);
                client.addPaymentMethod(acct, addr, name, routing);
                return client.getPaymentMethods().get(0);
            }
            default -> {
                System.out.println("Invalid choice.");
                return null;
            }
        }
    }

    // =========================================================
    // Consultant Menu (still placeholder)
    // =========================================================
    public static void runConsultantMenu(Scanner scanner, Consultant consultant) {

        while (true) {

            System.out.println("\n--- Consultant Menu ---");
            System.out.println("1. Manage Availability");
            System.out.println("2. Accept/Reject Booking");
            System.out.println("3. Complete Booking");
            System.out.println("4. Logout");

            int choice = readInt(scanner);

            if (choice == 1) {
                System.out.println("Managing Availability...");
                // consultant.manageAvailability(...);
            } else if (choice == 2) {
                System.out.println("Accepting/Rejecting Booking...");
                // consultant.acceptBooking(...); consultant.rejectBooking(...);
            } else if (choice == 3) {
                System.out.println("Completing Booking...");
                // consultant.completeBooking(...);
            } else if (choice == 4) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    // =========================================================
    // Admin Menu (still placeholder)
    // =========================================================
    public static void runAdminMenu(Scanner scanner, Admin admin) {

        while (true) {

            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Approve Consultant Registration");
            System.out.println("2. Define System Policies");
            System.out.println("3. Logout");

            int choice = readInt(scanner);

            if (choice == 1) {
                System.out.println("Approving Consultant...");
                // admin.approveConsultant(...);
            } else if (choice == 2) {
                System.out.println("Defining System Policies...");
                // policy.PolicyManager.getInstance().setPricingStrategy(...);
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    // =========================================================
    // Services display
    // =========================================================
    public static void browseServices(List<Service> services) {

        System.out.println("\n--- Services ---");

        for (int i = 0; i < services.size(); i++) {
            Service s = services.get(i);

            System.out.printf(
                    "%d) %s ($%.2f) - %s%n",
                    i + 1,
                    s.getName(),
                    s.getPrice(),
                    s.getDescription()
            );
        }
    }

    // =========================================================
    // Small helpers
    // =========================================================
    private static int readInt(Scanner scanner) {
        while (true) {
            String s = scanner.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.print("Enter a number: ");
            }
        }
    }
}
// import java.util.*;
// import service.*;

// public class Main {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         List<Service> services = new ArrayList<>();
//         services.add(new Service("Consulting", "Intro session", 100.0));
//         services.add(new Service("Resume Review", "30-min review", 60.0));
//         services.add(new Service("Mock Interview", "45-min interview", 120.0));
//         System.out.println("Welcome to the Consultancy System");
//         System.out.println("1. Login\n2. Exit");
//         int choice = scanner.nextInt();
//         scanner.nextLine(); // Consume newline
//         while(true){
//             if (choice == 1) {
//                 System.out.print("Enter your User ID (e.g., CL-1): ");
//                 String id = scanner.nextLine();
//                 // Logic to find user by ID and check their type
//                 System.out.println("Logged in as: " + id);
//                 showClientMenu();
//             } 
//             else {
//                 System.out.println("Exiting...");
//                 System.exit(0);
//             }
//             while (true) { 
//                 choice = scanner.nextInt();
//                 scanner.nextLine(); // Consume newline
//                 if(choice == 1) {
//                     // Browse Services
//                     System.out.println("Browsing Services...");
//                     browseServices(services);
//                 }
//                 else if(choice == 2) {
//                     // View Booking History
//                     System.out.println("Viewing Booking History...");
//                 }
//                 else if(choice == 3) {
//                     // Manage Payment Methods
//                     System.out.println("Managing Payment Methods...");
//                 }
//                 else if(choice == 4) {
//                     // Cancel Booking
//                     System.out.println("Cancelling Booking...");
//                 }
//                 else if(choice == 5) {
//                     // Request Booking
//                     System.out.println("Requesting Booking...");
//                 }
//                 else if(choice == 6) {
//                     // Process Payment
//                     System.out.println("Processing Payment...");
//                 }
//                 else if(choice == 7) {
//                     // View Payment History
//                     System.out.println("Viewing Payment History...");
//                 }
//                 if(choice == 8) {
//                     System.out.println("Are you sure you want to logout? (Y/N)");
//                     String logoutChoice = scanner.nextLine();
//                     if(logoutChoice.equalsIgnoreCase("Y")) {
//                         System.out.println("Logging out...");
//                         break; // Exit to main menu
//                     } else {
//                         System.out.println("Logout cancelled.");
//                         showClientMenu(); // Return to client menu
//                     }
//                 }
//                 else {
//                     System.out.println("Invalid choice. Please try again.");
//                 }
//             }
//         }
//         // // 1. Create a Registry to hold our users
//         // UserRegistry registry = new UserRegistry();
//         // // 2. Pre-load ("Seed") the users
//         // // These will automatically get IDs: CL-1, CO-1, and AD-1
//         // Client defaultClient = new Client("John Doe", "john@email.com", "555-0001");
//         // Consultant defaultConsultant = new Consultant("Jane Smith", "jane@email.com", "555-0002");
//         // Admin defaultAdmin = new Admin("System Admin", "admin@firm.com", "555-9999");
//         // // 3. Add them to the registry
//         // registry.addUser(defaultClient);
//         // registry.addUser(defaultConsultant);
//         // registry.addUser(defaultAdmin);
//         // // 4. Start the CLI and pass it the registry so it can "find" these users
//         // ConsultancyApp app = new ConsultancyApp();
//         // app.start();
//     }
//     public static void browseServices(List<Service> services) {
//         // Logic to display services
//         System.out.println("\n--- Services ---");
//             for (int i = 0; i < services.size(); i++) {
//                 Service s = services.get(i);
//                 System.out.printf("%d) %s ($%.2f) - %s%n", i + 1, s.getName(), s.getPrice(), s.getDescription());
//             }
//     }
//     public static void showClientMenu() {
//         System.out.println("\n--- Client Menu ---");
//         System.out.println("1. Browse Services\n2. View Booking History\n3. Manage Payment Methods\n4. Cancel Booking\n5. Request Booking\n6. Process Payment\n7. View Payment History\n8. Logout\n");
//         // Implement logic for selecting times here
//     }
// }
