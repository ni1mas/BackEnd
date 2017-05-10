package NiUnaMas.Daos;

import NiUnaMas.Models.Contact;
import NiUnaMas.Models.User;
import NiUnaMas.Models.UserContact;
import NiUnaMas.Models.UserContactPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 05/05/2017.
 */
public interface UserContactDao extends CrudRepository<UserContact, UserContactPK>{
}
