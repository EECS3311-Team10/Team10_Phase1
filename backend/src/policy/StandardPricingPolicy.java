package policy;

import service.Service;

public class StandardPricingPolicy implements PricingStrategy{
	// Standard pricing policy: returns the base price of the service without any discounts or adjustments
	@Override
	public double calculatePrice(Service service) {
		return service.getPrice();
	}
}
