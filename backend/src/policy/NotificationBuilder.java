package policy;

import Users.User;
import java.time.LocalDateTime;
import java.util.UUID;

public class NotificationBuilder {

    private String id;
    private String message;
    private LocalDateTime timestamp;
    private int priority;
    private User recipient;

    public void reset() {
        id = UUID.randomUUID().toString();
        message = "";
        timestamp = LocalDateTime.now();
        priority = 0;
        recipient = null;
    }

    public void buildMessage(String msg) {
        message = msg;
    }

    public void buildPriority(int priority) {
        this.priority = priority;
    }

    public void chooseUser(User user) {
        this.recipient = user;
    }

    public Notification build() {
        return new Notification(id, message, timestamp, priority, recipient);
    }
}