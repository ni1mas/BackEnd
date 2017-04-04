package NiUnaMas.daos;

import NiUnaMas.Models.Contact;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Robert on 05/04/2017.
 */
public interface ContactDao extends CrudRepository<Contact, Long> {

}
