package policy;

import bookingstates.Booking;
import service.Service;

public class DiscountStrategy implements PricingStrategy{
    // Discount strategy: applies a fixed discount rate to the base price of the service
    private final double discountRate = 0.10;

    @Override
    public double calculatePrice(Service service) {
        return service.getPrice() * (1 - discountRate); 
    }
}
