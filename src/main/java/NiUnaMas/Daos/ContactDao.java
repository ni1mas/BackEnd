package NiUnaMas.Daos;

import NiUnaMas.Models.Contact;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Robert on 05/04/2017.
 */
@Transactional
public interface ContactDao extends CrudRepository<Contact, Long> {
    Contact findByEmail(String email);
    Contact findByPhone(int id);
    Contact findByDni(String dni);
    Contact findByPhoneAndDniAndEmail(int phone, String Dni, String email);
    Contact findByActivationCode(String code);
}
