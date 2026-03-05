package payment;

public class CreditCardStrategy implements PaymentStrategy {
    private CreditCard creditCard;
    public CreditCardStrategy(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
    
    @Override
    public void pay() {
        System.out.println("Processing payment with credit card: " + creditCard);
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return creditCard;
    }

    @Override
    public void simPaymentProcessing() {
        // Simulate payment processing for credit card
        System.out.println("Simulating credit card payment processing...");
    }
}
