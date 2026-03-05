package payment;

public interface PaymentStrategy {
	public void pay();
	public PaymentMethod getPaymentMethod();
	public void simPaymentProcessing();
}
