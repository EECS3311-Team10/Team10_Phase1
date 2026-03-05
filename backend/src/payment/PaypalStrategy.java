package payment;

public class PaypalStrategy implements PaymentStrategy {
    private PayPal payPal;
    
    public PaypalStrategy(PayPal payPal) {
        this.payPal = payPal;
    }
    
    @Override
    public void pay() {
        // Implement PayPal payment logic here
        System.out.println("Processing payment through PayPal for " + payPal.getPaymentDetails());
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return payPal;
    }

    @Override
    public void simPaymentProcessing() {
        // Simulate payment processing for PayPal
        System.out.println("Simulating PayPal payment processing...");
    }

}
