package policy;

import bookingstates.Booking;
import service.Service;

public interface PricingStrategy {
	double calculatePrice(Service service,Booking booking);
}
