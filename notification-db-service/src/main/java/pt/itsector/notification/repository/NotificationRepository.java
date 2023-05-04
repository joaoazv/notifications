package pt.itsector.notification.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.itsector.notification.models.NotificationEntity;

@Repository
public interface NotificationRepository extends CrudRepository<NotificationEntity, Integer> {
}