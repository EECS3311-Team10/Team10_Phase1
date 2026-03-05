package bookingstates;
import java.time.LocalDateTime;

public class Booking {
    private String bookingID;
    private BookingState state;
    private LocalDateTime requestedTime;
    private LocalDateTime scheduledTime;
    private double price;

    public Booking(String bookingID, LocalDateTime requestedTime, LocalDateTime scheduledTime, double price) {
        this.bookingID = bookingID;
        this.requestedTime = requestedTime;
        this.scheduledTime = scheduledTime;
        this.price = price;
        this.state = new RequestedState();
    }

    // State transition - getter and setter for state
    public void setState(BookingState state) {
        this.state = state;
    }

    public BookingState getState() {
        return state;
    }

    // Forward actions to current state
    public void request() {
        state.handleRequest(this);
    }

    public void confirm() {
        state.handleConfirm(this);
    }

    public void reject() {
        state.handleReject(this);
    }

     public void complete() {
        state.handleComplete(this);
    }
    public void cancel() {
        state.handleCancel(this);
    }
    public void processPayment() {
        state.handlePayment(this);
    }

    // Getters and setters for other fields
    public String getBookingID() {
        return bookingID;
    }
    public LocalDateTime getRequestedTime() {
        return requestedTime;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingID='" + bookingID + '\'' +
                ", state=" + state.getClass().getSimpleName() +
                ", requestedTime=" + requestedTime +
                ", scheduledTime=" + scheduledTime +
                ", price=" + price +
                '}';
    }
}

