package policy;
import Users.User;
import java.util.ArrayList;
import java.util.List;

public class NotificationService {

    private List<Notification> notifications = new ArrayList<>();
    private List<Notification> userNotifications = new ArrayList<>();

    public void sendToUser(List<User> userlist, String notif) {
        if (userlist == null || notif == null) return;

        NotificationBuilder builder = new NotificationBuilder();

        for (User user : userlist) {
            builder.reset();
            builder.buildMessage(notif);
            builder.buildPriority(0);
            builder.chooseUser(user);

            Notification n = builder.build();
            notifications.add(n);
        }
    }

    public void markAsRead(String notifID) {
        if (notifID == null) return;

        for (Notification n : notifications) {
            if (notifID.equals(n.getId())) {
                n.markAsRead();
                return;
            }
        }
    }

    public List<Notification> getUserNotifications(User user) {
        userNotifications.clear();
        if (user == null) return new ArrayList<>();

        for (Notification n : notifications) {
            if (user.equals(n.getRecipient())) {
                userNotifications.add(n);
            }
        }
        return new ArrayList<>(userNotifications);
    }

    public void deleteNotification(String notifID) {
        if (notifID == null) return;

        notifications.removeIf(n -> notifID.equals(n.getId()));
        userNotifications.removeIf(n -> notifID.equals(n.getId()));
    }
}