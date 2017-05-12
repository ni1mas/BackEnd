package NiUnaMas.Controller;

import NiUnaMas.Api.ResponseApiDoc;
import NiUnaMas.Controller.Exceptions.InvalidCredentialsLoginException;
import NiUnaMas.Controller.Exceptions.UserDoesNotExistException;
import NiUnaMas.Daos.ResponseDao;
import NiUnaMas.Daos.ToContactDAO;
import NiUnaMas.Models.Response;
import NiUnaMas.Models.ResponseToContactDTO;
import NiUnaMas.Models.SuccessfulAction;
import NiUnaMas.Models.ToContact;
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
 * Created by Robert on 09/05/2017.
 */
@RestController
@RequestMapping(Uris.SERVLET_MAP+Uris.RESPONSE)
public class ResponseController implements ResponseApiDoc{
    @RequestMapping(value = "/send", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessfulAction sendResponse(@RequestBody ResponseToContactDTO dto) {
        ToContact toContact = dto.getTc();
        try {
            if(dto.getTc() == null){
                responseDao.save(new Response(dto.getResp()));
            }else{
                toContact = toContactDAO.findByPhone(dto.getTc().getPhone());
                if(toContact == null){
                    ToContact tc = dto.getTc();
                    tc.setResponse(responseDao.save(new Response(dto.getResp())));
                    toContactDAO.save(tc);
                }
            }
            return new SuccessfulAction("200", "Response sent successfuly.");
        }catch(Exception e){
            throw new UserDoesNotExistException(e.toString());
        }
    }

    @Autowired
    private ToContactDAO toContactDAO;
    @Autowired
    private ResponseDao responseDao;
}
