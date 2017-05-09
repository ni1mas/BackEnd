package NiUnaMas.Controller;

import NiUnaMas.Api.ContactApiDoc;
import NiUnaMas.Controller.Exceptions.InvalidCredentialsLoginException;
import NiUnaMas.Controller.Exceptions.UserDoesNotExistException;
import NiUnaMas.Daos.ContactDao;
import NiUnaMas.Daos.UserContactDao;
import NiUnaMas.Daos.UserDao;
import NiUnaMas.Models.*;
import NiUnaMas.Varios.Uris;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Robert on 05/04/2017.
 */
@RestController
@RequestMapping(Uris.SERVLET_MAP+Uris.USER+Uris.ID+Uris.CONTACT)
public class ContactController implements ContactApiDoc{
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessfulAction addContact(@ApiParam(value = "Contact that the user allows to get notifications." ,required=true )
                                             @RequestBody Contact contact, @ApiParam(value = "Id of the user") @PathVariable String id) {
        try{
            User user = userDao.findById(id);
            if(userDao.findById(id)==null)
                throw new UserDoesNotExistException("");
            else{
                contactDao.save(contact);
                ArrayList<UserContact> uc = new ArrayList<>();
                uc.add(new UserContact(user, contactDao.findByEmail(contact.getEmail()), "novios"));
                userContactDao.save(uc);
                return new SuccessfulAction("200", "Contact creted succesfully.");
            }
        }catch(UserDoesNotExistException e){
            throw new UserDoesNotExistException("The user does not exists");
        }

    }
    @RequestMapping(value = "/remove", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessfulAction removeContact(@ApiParam(value = "Contact that the user wants to delete." ,required=true )
                                             @RequestBody Contact contact, @PathVariable String id) {
        try{
            User user = userDao.findById(id);
            if(user==null)
                throw new UserDoesNotExistException("");
            else{
                contactDao.delete(contact);
                return new SuccessfulAction("200", "Contact deleted succesfully.");
            }
        }catch(UserDoesNotExistException e){
            throw new UserDoesNotExistException("The user does not exists");
        }
    }
    @RequestMapping(value = "/getContacts", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessfulAction getContacts(@ApiParam(value = "Returns all the contacts the user has." ,required=true )
                                          @RequestBody Contact contact, @PathVariable String id) {
        try{
            User user = userDao.findById(id);
            if(user==null)
                throw new UserDoesNotExistException("");
            else{
                List <Object> list = (List)userContactDao.findAll();
                return new SuccessfulAction("200", "Contact deleted succesfully.", list);
            }
        }catch(UserDoesNotExistException e){
            throw new UserDoesNotExistException("The user does not exists");
        }
    }

    @Autowired
    UserDao userDao;
    @Autowired
    ContactDao contactDao;
    @Autowired
    UserContactDao userContactDao;
}
