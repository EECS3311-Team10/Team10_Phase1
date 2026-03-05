package payment;

public class CreditCard extends PaymentMethod{
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;

    public CreditCard(String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        super("Credit Card");
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public boolean validate() {
        // Validation logic for credit card

        boolean validated = true;
        // Verify card number length = 16
        String s = String.valueOf(this.cardNumber);
        if (s.length() != 16) {
            validated = false;
        }
        // Verify expiryDate >> current date
        

        return validated;
    }
    @Override
    public String getPaymentDetails() {
        return "Credit Card: " + cardNumber;
    }
}
