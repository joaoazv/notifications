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
public class DBNotification {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(name = "ID")
  private Integer id;

  @Column(name = "NOTIFICATION_ID", unique = true)
  private String notificationId;

  @Column(name = "STATUS")
  private Integer status;

  @Column(name = "DESTINATION")
  private String destination;

  @Column(name = "SUBJECT")
  private String subject;

  @Column(name = "MESSAGE")
  private String message;

  @Column(name = "CREATION_DATE_TIME")
  private OffsetDateTime creationDateTime;

  @Column(name = "SENDING_DATE_TIME")
  private OffsetDateTime sendingDateTime;

  @Column(name = "STATUS_MESSAGE")
  private String statusMessage;
}