package pt.itsector.notification.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.itsector.notification.models.DBNotification;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Repository
public interface NotificationRepository extends CrudRepository<DBNotification, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE DBNotification n " +
            "SET n.status = :status, n.sendingDateTime = :sendingDateTime, n.statusMessage = :statusMessage " +
            "WHERE n.notificationId = :notificationId")
    void updateNotificationResult(@Param(value = "notificationId" ) String notificationId,
                                  @Param(value = "status") Integer status,
                                  @Param(value = "sendingDateTime") OffsetDateTime sendingDateTime,
                                  @Param(value = "statusMessage") String statusMessage);
}