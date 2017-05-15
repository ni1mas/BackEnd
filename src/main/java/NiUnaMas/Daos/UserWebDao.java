package NiUnaMas.Daos;

import NiUnaMas.Models.UserWeb;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Robert on 15/05/2017.
 */
@Transactional
public interface UserWebDao extends CrudRepository <UserWeb, String> {
    UserWeb findById(String id);
}
