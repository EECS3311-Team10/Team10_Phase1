package payment;

public class PaypalStrategy implements PaymentStrategy {
    private String email;
    private String password;
    
    public PaypalStrategy(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    @Override
    public void pay() {
        // Implement PayPal payment logic here
        System.out.println("Processing payment through PayPal for " + email);
    }

}
