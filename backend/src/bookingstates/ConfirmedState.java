package bookingstates;

public class ConfirmedState extends BaseBookingState {
    @Override
    public void handleCancel(Booking booking) {
        booking.setState(new CancelledState());
    }

    @Override
    public void handlePayment(Booking booking) {
        booking.setState(new PendingPaymentState());
    }
}
