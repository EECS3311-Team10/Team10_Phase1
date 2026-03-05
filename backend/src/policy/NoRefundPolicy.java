package policy;

import bookingstates.Booking;

public class NoRefundPolicy implements RefundPolicy{
    // No refund policy: always returns 0, indicating that no refund will be given regardless of the booking details
    @Override
	public double calculateRefund(Booking booking) {
		return 0;
	}
}
