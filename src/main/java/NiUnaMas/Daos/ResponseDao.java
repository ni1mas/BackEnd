package NiUnaMas.Daos;

import NiUnaMas.Controller.ResponseController;
import NiUnaMas.Models.Response;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Robert on 09/05/2017.
 */
public interface ResponseDao extends CrudRepository<Response,Long> {
}
