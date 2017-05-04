package NiUnaMas.Controller;
import NiUnaMas.Daos.AnswerDao;
import NiUnaMas.Daos.PollDao;
import NiUnaMas.Models.Answers;
import NiUnaMas.Models.Poll;
import NiUnaMas.Models.SuccessfulAction;
import NiUnaMas.Varios.Uris;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 04/05/2017.
 */
@RestController
@RequestMapping(Uris.SERVLET_MAP)
public class PollController {
    /*@RequestMapping(value = "/polls", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessfulAction sendAnonPoll(@ApiParam(value = "Anonimous poll." ,required=true )@RequestBody Poll poll) {
        pollDao.save(poll);
        for (int i =0;i<poll.getAnswers().size();i++){
            answerDao.save(new Answers(poll.getAnswers().get(i)));
        }
        List<Object> list = new ArrayList<>();
        return new SuccessfulAction("200", "Logged successfully.", list);
    }

    @Autowired
    private PollDao pollDao;
    @Autowired
    private AnswerDao answerDao;*/
}
