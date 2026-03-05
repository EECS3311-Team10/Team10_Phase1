package payment;

public class DebitCard extends PaymentMethod{
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;

    public DebitCard(String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        super("Debit Card");
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public boolean validate() {
        // Implement validation logic for debit card

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
        return "Debit Card: " + cardNumber;
    }
}
