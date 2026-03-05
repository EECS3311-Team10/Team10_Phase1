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
    // credit card and debit card
    public boolean addPaymentMethod(String paymentType, String cardNumber, YearMonth expiryDate,
            String cvv, String cardHolderName) {

        if (paymentType.equals("CREDIT")) {
            CreditCard creditCard = new CreditCard(cardNumber, cardHolderName,
                    expiryDate, cvv);
            if (!creditCard.validate()) {
                return false;
            }
            paymentMethods.add(creditCard);
            System.out.println("Your credit card payment method was added.");
            // notification
        } else if (paymentType.equals("DEBIT")) {
            DebitCard debitCard = new DebitCard(cardNumber, cardHolderName,
                    expiryDate, cvv);
            if (!debitCard.validate()) {
                return false;
            }
            paymentMethods.add(debitCard);
            System.out.println("Your debit card payment method was added.");
        }
        return true;
    }

    // bank transfer
    public boolean addPaymentMethod(String accountNumber, String address, String accountName, String routingNumber) {
        BankTransfer bankTransfer = new BankTransfer(accountNumber, address, accountName, routingNumber);
        if (!bankTransfer.validate()) {
            return false;
        }
        paymentMethods.add(bankTransfer);
        System.out.println("Your new payment method was added.");
        // notification
        paymentMethods.add(bankTransfer);
        System.out.println("Your bank account as a payment method has been added");

        return true;
    }

    public boolean addPaymentMethod(String email) {
        PayPal payPal = new PayPal(email);
        if (!payPal.validate()) {
            return false;
        }
        paymentMethods.add(payPal);
        System.out.println("Your PayPal as a payment method has been added");

        return true;
    }

    public void addPayment(Payment payment) {
        this.paymentHistory.add(payment);
    }

    public List<?> viewPaymentHistory() {
        return this.paymentHistory;
    }
}
