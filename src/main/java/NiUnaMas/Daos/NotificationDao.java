package NiUnaMas.Daos;

/**
 * Created by Robert on 03/04/2017.
 */


import javax.transaction.Transactional;

import NiUnaMas.Models.Notification;
import NiUnaMas.Models.User;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Transactional
public interface NotificationDao extends CrudRepository<Notification, Long> {
    Notification getByUser(User user);
    List<Notification> findByUser(User user);
    List<Notification> findAllByOrderByDateDesc();
    Notification getByUserOrderByDateDesc(User user);
} // class NotificationDao
