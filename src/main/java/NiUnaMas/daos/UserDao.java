package NiUnaMas.daos;

import NiUnaMas.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Robert on 04/04/2017.
 */

@Transactional
public interface UserDao extends CrudRepository<User, Long> {
    @Query("select user from User user where user.email = ?1 and user.password = ?2")
    List<User> miFuncion(String email, String password);
}
