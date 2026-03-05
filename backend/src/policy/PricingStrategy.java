package policy;

import service.Service;

public interface PricingStrategy {
	double calculatePrice(Service service);
}
