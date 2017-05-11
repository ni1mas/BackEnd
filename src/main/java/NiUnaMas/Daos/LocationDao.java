package NiUnaMas.Daos;

import NiUnaMas.Models.Location;
import NiUnaMas.Models.LocationPK;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Robert on 11/05/2017.
 */
@Transactional
public interface LocationDao extends CrudRepository<Location, LocationPK> {
}
