package Users;
import bookingstates.*;
import payment.*;
import java.util.List;
import java.util.ArrayList;

public class Client extends User {

    private static int idCounter = 1; 
    
    private List<PaymentMethod> paymentMethods;
    private List<Booking> bookings;

    public Client(String name, String email, String phone) {
        super(name, email, phone); 
        this.setRole("Client");
        this.paymentMethods = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.userId = "CL-" + idCounter;
        incrementCounter();
        
    }

     public static void incrementCounter(){
        idCounter++;
    }

    public void requestBooking(Booking booking) {
        this.bookings.add(booking);
    }

    public void cancelBooking(Booking booking) {
        this.bookings.remove(booking);
    }

    public ArrayList<Booking> viewBookingHistory() {
        return this.bookings;
    }

    public void processPayment(Payment payment, PaymentStrategy strategy) {
        payment.process(strategy); 
    }

    public ArrayList<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void addPaymentMethod(PaymentMethod payMethod){
        this.paymentMethods.add(payMethod);

    }
}
