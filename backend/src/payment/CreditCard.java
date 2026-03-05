package payment;

import java.time.*;

public class CreditCard extends PaymentMethod {

    private String cardNumber;
    private String cardHolderName;
    private YearMonth expiryDate;
    private final String CVV;

    public CreditCard(String cardNumber, String cardHolderName, YearMonth expiryDate, String CVV) {
        super("Credit Card");
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.CVV = CVV;
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
        if (expiryDate.isBefore(YearMonth.now())) {
            validated = false;
        }

        return validated;
    }

    @Override
    public String getPaymentDetails() {
        return "Credit Card: " + cardNumber;
    }
}
