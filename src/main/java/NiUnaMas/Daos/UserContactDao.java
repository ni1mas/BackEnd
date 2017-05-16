package NiUnaMas.Daos;

import NiUnaMas.Models.Contact;
import NiUnaMas.Models.User;
import NiUnaMas.Models.UserContact;
import NiUnaMas.Models.UserContactPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 05/05/2017.
 */
@Transactional
public interface UserContactDao extends CrudRepository<UserContact, UserContactPK>{
    int deleteUserContactById(UserContactPK id);
    UserContact findById(UserContactPK id);
    @Query("SELECT uc FROM UserContact uc WHERE uc.contact_id = :id")
    public List<UserContact> find(@Param("id") Contact contact);
}
