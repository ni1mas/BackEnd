package NiUnaMas.Daos;

import NiUnaMas.Controller.ResponseController;
import NiUnaMas.Models.Response;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Robert on 09/05/2017.
 */
@Transactional
public interface ResponseDao extends CrudRepository<Response,Long> {
}
