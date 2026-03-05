package payment;

public class DebitCardStrategy implements PaymentStrategy {
    private final int cardNumber;
    private String cardHolderName;
    private String expiryDate;
    public DebitCardStrategy(int cardNumber, String cardHolderName, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
    }
    
    @Override
    public void pay() {
        // Implement payment logic for debit card
        System.out.println("Processing debit card payment...");
    }
    
    @Override
    public PaymentMethod getPaymentMethod() {
        return null;
    }
    @Override
    public void simPaymentProcessing() {
        // Simulate payment processing for debit card
        System.out.println("Simulating debit card payment processing...");
    }
}
