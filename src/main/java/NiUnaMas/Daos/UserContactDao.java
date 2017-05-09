package NiUnaMas.Daos;

import NiUnaMas.Models.UserContact;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by Robert on 05/05/2017.
 */
public interface UserContactDao extends CrudRepository<UserContact, Long> {
    //find
}
