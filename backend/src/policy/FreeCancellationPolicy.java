package policy;

import bookingstates.Booking;
import java.time.Duration;
import java.time.LocalDateTime;

public class FreeCancellationPolicy implements CancellationPolicy {
    // Free cancellation policy: allows free cancellation if the booking is canceled at least 48 hours before the scheduled time
    @Override
    public boolean isAllowed(Booking booking) {
        long hoursUntilBooking = Duration.between(LocalDateTime.now(), booking.getScheduledTime()).toHours();
        return hoursUntilBooking <= 48;
    }
}