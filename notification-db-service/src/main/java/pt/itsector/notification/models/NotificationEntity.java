package pt.itsector.notification.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NOTIFICATIONS")
public class NotificationEntity {
  @Id
  @Column(name = "NOTIFICATION_ID")
  private String notificationId;

  @Column(name = "DESTINATION")
  private String destination;

  @Column(name = "SUBJECT")
  private String subject;

  @Column(name = "MESSAGE")
  private String message;

  @Column(name = "CREATION_DATE")
  private OffsetDateTime creationDate;
}