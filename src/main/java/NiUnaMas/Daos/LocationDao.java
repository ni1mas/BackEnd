package NiUnaMas.Daos;

import NiUnaMas.Models.Location;
import NiUnaMas.Models.LocationPK;
import NiUnaMas.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Robert on 11/05/2017.
 */
@Transactional
public interface LocationDao extends CrudRepository<Location, LocationPK> {
    //List<Location> findByUser_dni(User dni);
    @Query("SELECT l FROM Location l WHERE l.user_dni = :user_dni")
    List<Location> getAllByUser_dni(@Param("user_dni") User user_dni);
}
