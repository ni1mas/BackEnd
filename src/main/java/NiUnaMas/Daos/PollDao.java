package NiUnaMas.Daos;

import NiUnaMas.Models.Poll;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Robert on 04/05/2017.
 */
public interface PollDao extends CrudRepository<Poll, Long> {

}
