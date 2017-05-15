package NiUnaMas.Daos;

import NiUnaMas.Models.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Robert on 04/04/2017.
 */

@Transactional
public interface UserDao extends CrudRepository<User, Long> {
    User getUserByEmailAndPassword(String email, String password);
    User findById(String id);
    User findByName(String name);
    User findByEmail(String email);
    User findByDni(String dni);
}
