package NiUnaMas.daos;

import NiUnaMas.Models.Authorization;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Robert on 09/04/2017.
 */
public interface AuthorizationDao extends CrudRepository<Authorization, Long> {
}
