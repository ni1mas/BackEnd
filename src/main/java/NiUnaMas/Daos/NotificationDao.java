package NiUnaMas.Daos;

/**
 * Created by Robert on 03/04/2017.
 */


import javax.transaction.Transactional;

import NiUnaMas.Models.Notification;
import NiUnaMas.Models.User;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface NotificationDao extends CrudRepository<Notification, Long> {
    Notification getByUser(User user);
} // class NotificationDao
