package policy;

import Users.User;
import java.time.LocalDateTime;

public class Notification {

    private final String id;
    private final String message;
    private final LocalDateTime timestamp;
    private final int priority;
    private final User recipient;

    private boolean read;

    public Notification(String id, String message, LocalDateTime timestamp, int priority, User recipient) {
        this.id = id;
        this.message = message;
        this.timestamp = timestamp;
        this.priority = priority;
        this.recipient = recipient;
        this.read = false;
    }

    public String getId() { return id; }
    public String getMessage() { return message; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public int getPriority() { return priority; }

    public User getRecipient() { return recipient; }

    public boolean isRead() { return read; }

    public void markAsRead() {
        read = true;
    }
}