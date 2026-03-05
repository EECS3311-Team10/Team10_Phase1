package policy;

import bookingstates.Booking;
import service.Service;

public class DiscountStrategy implements PricingStrategy{
    private final double discountRate = 0.10;

    @Override
    public double calculatePrice(Service service, Booking booking) {
        return service.getPrice() * (1 - discountRate); 
    }
}
