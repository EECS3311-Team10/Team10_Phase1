package payment;

public abstract class PaymentMethod {
    private static int idCounter = 1; // static counter for generating unique payment method IDs
    protected int paymentMethodId;
    protected String paymentType;
    // Constructor
    public PaymentMethod(String paymentType) {
        this.paymentMethodId = idCounter++;
        this.paymentType = paymentType;
    }

    // Getters for payment method ID and type
    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public abstract boolean validate();
    public abstract String getPaymentDetails();

}
