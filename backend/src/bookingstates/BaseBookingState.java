package bookingstates;
public abstract class BaseBookingState implements BookingState {
    @Override
    public void handleRequest(Booking booking) {}

    @Override
    public void handleConfirm(Booking booking) {}

    @Override
    public void handleReject(Booking booking) {}

    @Override
    public void handleCancel(Booking booking) {}

    @Override
    public void handlePayment(Booking booking) {}

    @Override
    public void handleComplete(Booking booking) {}

}