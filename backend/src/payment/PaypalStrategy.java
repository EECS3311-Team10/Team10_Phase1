package payment;

public class PaypalStrategy implements PaymentStrategy {
    private final String email;
    private final String password;
    
    public PaypalStrategy(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    @Override
    public void pay() {
        // Implement PayPal payment logic here
        System.out.println("Processing payment through PayPal for " + email);
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return null;
    }

    @Override
    public void simPaymentProcessing() {
        // Simulate payment processing for PayPal
        System.out.println("Simulating PayPal payment processing...");
    }

}
