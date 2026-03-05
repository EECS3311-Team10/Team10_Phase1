package payment;

public class CreditCardStrategy implements PaymentStrategy {
    private CreditCard creditCard;
    public CreditCardStrategy(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
    
    @Override
    public void pay() {
        // Implement payment logic using credit card details
        System.out.println("Processing payment with credit card: " + creditCard);
    }

}
