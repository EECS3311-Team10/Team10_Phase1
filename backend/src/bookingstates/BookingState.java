package bookingstates;

public interface BookingState {
	void handleRequest(Booking b);
    void handleConfirm(Booking b);
    void handleCancel(Booking b);
    void handlePayment(Booking b);
    void handleComplete(Booking b);
    void handleReject(Booking b);
}
