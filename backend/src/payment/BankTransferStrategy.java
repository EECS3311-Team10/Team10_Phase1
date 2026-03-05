package payment;

public class BankTransferStrategy implements PaymentStrategy {
    private BankTransfer bankTransfer;
    
    public BankTransferStrategy(BankTransfer bankTransfer) {
        this.bankTransfer = bankTransfer;
    }
    
    @Override
    public void pay() {
        // Implement payment logic using bank transfer
        System.out.println("Processing payment through bank transfer...");
    }

}
