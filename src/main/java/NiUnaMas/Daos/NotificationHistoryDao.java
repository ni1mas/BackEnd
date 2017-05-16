package NiUnaMas.Daos;

import NiUnaMas.Models.NotificationHistory;
import NiUnaMas.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Robert on 15/05/2017.
 */
public interface NotificationHistoryDao extends CrudRepository <NotificationHistory, Integer> {
    List<NotificationHistory> findNotificationHistoryByApp(String app);
}
