package Users;

import bookingstates.*;
import java.util.*;
import service.Service;

public class Consultant extends User {

    private static int idCounter = 1;

    private boolean isApproved = false;
    private ArrayList<Booking> bookings;
    private ArrayList<Service> services;

    public Consultant(String name, String email, String phone) {
        super(name, email, phone, "Consultant");
        this.setRole("Consultant");
        this.bookings = new ArrayList<>();
        this.services = new ArrayList<>();
        this.userId = "CO-" + idCounter++;
    }

    /* 
  public void manageAvailability(){

  }
     */
    public List<Booking> getPendingBookingRequests() {
        return null;
    }

    // =========================================
    // Booking management
    // =========================================
    public void acceptBooking(Booking booking) {

        if (booking == null) {
            return;
        }

        booking.confirm();  // State pattern transition
        bookings.add(booking);

        System.out.println("Booking accepted: " + booking.getBookingID());
    }

    public void rejectBooking(Booking booking) {

        if (booking == null) {
            return;
        }

        booking.reject();

        System.out.println("Booking rejected: " + booking.getBookingID());
    }

    public void completeBooking(Booking booking) {

        if (booking == null) {
            return;
        }

        booking.complete();

        System.out.println("Booking completed: " + booking.getBookingID());
    }

    // =========================================
    // Registration
    // =========================================
    public void register() {
        this.isApproved = false;
        System.out.println("Consultant registration submitted.");
    }

    // called by Admin
    public void approve() {
        this.isApproved = true;
        System.out.println("Consultant approved.");
    }

    // called by Admin
    public void reject() {
        this.isApproved = false;
        System.out.println("Consultant rejected.");
    }

    public boolean checkApproval() {
        return isApproved;
    }

}
