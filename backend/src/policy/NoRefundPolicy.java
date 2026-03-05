package policy;

import bookingstates.Booking;
import service.Service;

public class NoRefundPolicy implements RefundPolicy{
	public double calculateRefund(Service service,Booking booking) {
		return 0;
	}

    @Override
    public double calculateRefund(Booking booking) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
