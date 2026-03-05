package policy;

import bookingstates.Booking;
import service.Service;

public class StandardPricingPolicy implements PricingStrategy{

	public double calculatePrice(Service service,Booking booking) {
		return service.getPrice();
	}
}
