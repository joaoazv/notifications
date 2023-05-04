package pt.itsector.notification.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NOTIFICATIONS_RESULTS")
public class NotificationResultEntity {
  @Id
  @Column(name = "NOTIFICATION_ID")
  private String notificationId;

  @Column(name = "STATUS")
  private Integer status;

  @Column(name = "STATUS_DATE")
  private OffsetDateTime statusDate;

  @Column(name = "STATUS_MESSAGE")
  private String statusMessage;
}