package policy;
import bookingstates.Booking;
public interface RefundPolicy {
	double calculateRefund(Booking booking);
}
