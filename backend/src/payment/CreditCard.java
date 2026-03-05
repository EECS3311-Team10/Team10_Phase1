package payment;

public class CreditCard extends PaymentMethod{
    private int cardNumber;
    private String cardHolderName;
    private String expiryDate;

    public CreditCard(int cardNumber, String cardHolderName, String expiryDate) {
        super("Credit Card");
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean validate() {
        // Implement validation logic for credit card

        boolean validated = true;
        //verify card number length = 16
        String s = String.valueOf(this.cardNumber);
        if (s.length() != 16) {
            validated = false;
        }
        //verify expiryDate >> current date
        

        return validated;
    }
    @Override
    public String getPaymentDetails() {
        return "Credit Card: " + cardNumber;
    }
}
