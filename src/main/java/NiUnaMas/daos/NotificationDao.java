package NiUnaMas.daos;

/**
 * Created by Robert on 03/04/2017.
 */


import javax.transaction.Transactional;

import NiUnaMas.Models.Notification;
import NiUnaMas.Models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * A DAO for the entity User is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 * The magic is that such methods must not be implemented, and moreover it is
 * possible create new query methods working only by defining their signature!
 *
 * @author netgloo
 */
@Transactional
public interface NotificationDao extends CrudRepository<Notification, Long> {
    Notification getByUser(User user);
} // class NotificationDao
