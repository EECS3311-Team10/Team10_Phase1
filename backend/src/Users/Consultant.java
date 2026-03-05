package Users;
import bookingstates.*;
import java.util.*;
import service.Service;

public class Consultant extends User{

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
/* 
  public boolean acceptBooking(Booking booking){
    if (checkApproval() == false) {
      // inform that consultant is not approved
      return true;
    }
  }
    
    if(booking.getState() != RequestedState) { // fix  this
      throw new IllegalStateException(
        "Can only accept Bookings in REQUESTED state" );
    }

    booking.confirm();
    this.bookings.add(booking);

    // update the consultant stats

    // notification

    return true;
  }

  public boolean rejectBooking(Booking booking){
    if (checkApproval() == false) {
      // inform that consultant is not approved
      return true;
    }
  }
    if(booking.getState() != RequestedState) { // fix  this
      throw new IllegalStateException(
        "Can only reject Bookings in REQUESTED state" );
    }

    booking.reject();

    // update the consultant stats

    // notification

    return true;
  }
*/
  public void addService(){
    
  }

  public void removeService(String serviceID){

  }

  public boolean isAvailable(){
    
    return true; 
  }

  public void blockTimeSlot(){
    
  } 

  //should make a new class for registrations
  public void register() {

  }


  // called by Admin
  public void approve() {
    this.isApproved = true;

    //notification implemenentation
  }

  // called by Admin
  public void reject() {
    this.isApproved = false;
  }

  public boolean checkApproval() {
    return isApproved;
  }

}
