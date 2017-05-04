package NiUnaMas.Daos;

import NiUnaMas.Models.Answers;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Robert on 04/05/2017.
 */
public interface AnswerDao extends CrudRepository<Answers, Long> {

}
