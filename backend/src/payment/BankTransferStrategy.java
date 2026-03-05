package payment;

public class BankTransferStrategy implements PaymentStrategy {
    private BankTransfer bankTransfer;
    
    public BankTransferStrategy(BankTransfer bankTransfer) {
        this.bankTransfer = bankTransfer;
    }
    
    @Override
    public void pay() {
        System.out.println("Processing payment through bank transfer...");
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return bankTransfer;
    }

    @Override
    public void simPaymentProcessing() {
        // Simulate payment processing for bank transfer
        System.out.println("Simulating bank transfer payment processing...");
    }

}
