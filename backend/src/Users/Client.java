package Users;

import bookingstates.*;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import payment.*;

public class Client extends User {

    private static int idCounter = 1;

    private List<PaymentMethod> paymentMethods;
    private List<Booking> bookings;
    private List<Payment> paymentHistory;

    public Client(String name, String email, String phone) {
        super(name, email, phone, "Client");
        this.setRole("Client");
        this.paymentMethods = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.paymentHistory = new ArrayList<>();
        this.userId = "CL-" + idCounter++;
    }

    public void requestBooking(Booking booking) {
        this.bookings.add(booking);
    }

    public void cancelBooking(Booking booking) {
        this.bookings.remove(booking);
    }

    public List<Booking> viewBookingHistory() {
        return this.bookings;
    }

    public void processPayment(Payment payment, PaymentStrategy strategy) {
        //payment(strategy); 
    }

    public List<PaymentMethod> getPaymentMethods() {
        return this.paymentMethods;
    }

    // methods for adding paymentMethods
    // credit card
    public boolean addPaymentMethod(String cardNumber, LocalDate expiryDate,
            String cvv, String cardHolderName) {
        CreditCard creditCard = new CreditCard(cardNumber, cardHolderName,
                expiryDate, cvv);
        if (!creditCard.validate()) {
            return false;
        }
        paymentMethods.add(creditCard);
        System.out.println("Your new payment method was added.");
        // notification
        return true;
    }

    public void addPayment(Payment payment) {
        this.paymentHistory.add(payment);
    }

    public List<?> viewPaymentHistory() {
        return this.paymentHistory;
    }
}
