package NiUnaMas.Daos;

import NiUnaMas.Models.NotificationHistory;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Robert on 15/05/2017.
 */
public interface NotificationHistoryDao extends CrudRepository <NotificationHistory, Integer> {
}
