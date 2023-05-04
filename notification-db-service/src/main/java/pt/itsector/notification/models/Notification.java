package pt.itsector.notification.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification implements Comparable<Notification> {
    private String notificationId;

    private String destination;

    private String subject;

    private String message;

    private OffsetDateTime creationDate;

    private String status;

    private OffsetDateTime statusDate;

    private String statusMessage;

    @Override
    public int compareTo(Notification n) {
        if (this.getCreationDate() == null || n.getCreationDate() == null) {
            return 0;
        }
        return n.getCreationDate().compareTo(getCreationDate());
    }
}
