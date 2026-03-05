package policy;
import bookingstates.Booking;
public interface CancellationPolicy {
	boolean isAllowed(Booking booking);
}
