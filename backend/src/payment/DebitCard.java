package payment;

import java.time.*;

public class DebitCard extends PaymentMethod {

    private String cardNumber;
    private String cardHolderName;
    private YearMonth expiryDate;
    private String cvv;

    public DebitCard(String cardNumber, String cardHolderName, YearMonth expiryDate, String cvv) {
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
        if (expiryDate.isBefore(YearMonth.now())) {
            validated = false;
        }

        return validated;
    }

    @Override
    public String getPaymentDetails() {
        return "Debit Card: " + cardNumber;
    }
}
