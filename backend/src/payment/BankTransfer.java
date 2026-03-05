package payment;

public class BankTransfer extends PaymentMethod{
    private final String accountNumber;
    private final String address;
    private final String accountName;
    private final String routingNumber;

    public BankTransfer(String accountNumber, String address, String accountName, String routingNumber) {
        super("Bank Transfer");
        this.accountNumber = accountNumber;
        this.address = address;
        this.accountName = accountName;
        this.routingNumber = routingNumber;
    }

    @Override
    public boolean validate() {
        // validate is bank transfer possible
        return accountName != null && !accountName.isBlank();
    }
    @Override
    public String getPaymentDetails() {
        return "BankTransfer: " + accountName + " / acct " + accountNumber;
    }
}
