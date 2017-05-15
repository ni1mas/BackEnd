package NiUnaMas.Daos;

import NiUnaMas.Models.Location;
import NiUnaMas.Models.LocationPK;
import NiUnaMas.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Robert on 11/05/2017.
 */
@Transactional
public interface LocationDao extends CrudRepository<Location, LocationPK> {
}
