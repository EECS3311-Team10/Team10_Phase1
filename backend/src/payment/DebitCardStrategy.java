package payment;

public class DebitCardStrategy implements PaymentStrategy {
    private DebitCard debitCard;
    public DebitCardStrategy(DebitCard debitCard) {
        this.debitCard = debitCard;
    }
    
    @Override
    public void pay() {
        System.out.println("Processing debit card payment...");
    }
    
    @Override
    public PaymentMethod getPaymentMethod() {
        return debitCard;
    }
    @Override
    public void simPaymentProcessing() {
        // Simulate payment processing for debit card
        System.out.println("Simulating debit card payment processing...");
    }
}
