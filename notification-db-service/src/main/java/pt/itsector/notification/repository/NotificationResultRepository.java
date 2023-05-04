package pt.itsector.notification.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.itsector.notification.models.NotificationResultEntity;

@Repository
public interface NotificationResultRepository extends CrudRepository<NotificationResultEntity, Integer> {
}