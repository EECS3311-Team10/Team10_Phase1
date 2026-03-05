package policy;

import bookingstates.Booking;
import service.Service;

public class StandardPricingPolicy implements PricingStrategy{
	// Standard pricing policy: returns the base price of the service without any discounts or adjustments
	@Override
	public double calculatePrice(Service service, Booking booking) {
		return service.getPrice();
	}
}
