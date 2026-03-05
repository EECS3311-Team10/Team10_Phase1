package payment;

public class PayPal extends PaymentMethod{
    private String email;
    public PayPal(String email) {
        super("PayPal");
        this.email = email;
    }

    @Override
    public boolean validate() {
        // checks if email is a valid email
        return email != null && email.contains("@") && email.length() > 5 ; //smallest email a@b.cd
    }
    @Override
    public String getPaymentDetails() {
        return "PayPal: " + email;
    }
}
