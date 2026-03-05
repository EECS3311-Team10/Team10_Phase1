package policy;

import bookingstates.Booking;
import java.time.Duration;
import java.time.LocalDateTime;

public class FreeCancellationPolicy implements CancellationPolicy {

    @Override
    public boolean isAllowed(Booking booking) {
        //calculates if you are eligable based off scheduled time
        long hoursUntilBooking =
            Duration.between(LocalDateTime.now(), booking.getScheduledTime()).toHours();

        return hoursUntilBooking <= 24;
    }
}