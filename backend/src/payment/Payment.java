package payment;
import java.time.LocalDateTime;

public class Payment {
    private String paymentID;
    private double amount;
    private PaymentStatus status;
    private LocalDateTime timestamp;
    private String transactionID;

    // Constructor
    public Payment(String paymentID, double amount, PaymentStatus status, LocalDateTime timestamp, String transactionID) {
        this.paymentID = paymentID;
        this.amount = amount;
        this.status = status;
        this.timestamp = timestamp;
        this.transactionID = transactionID;
    }

    // Getters and Setters
    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
}
