package NiUnaMas.Controller;

import NiUnaMas.Api.ContactApiDoc;
import NiUnaMas.Controller.Exceptions.ContactAlreadyExists;
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
                                             @RequestBody ContactDTO dto, @ApiParam(value = "Id of the user") @PathVariable String id) {
        try{
            User user = userDao.findById(id);
            if(userDao.findById(id)==null)
                throw new UserDoesNotExistException("");
            else{
                List<UserContact> list;
                boolean ok = true;
                Contact test = contactDao.findByPhoneAndDniAndEmail(dto.getContact().getPhone(), dto.getContact().getDni(), dto.getContact().getEmail());
                if(test == null){
                    contactDao.save(dto.getContact());
                    ArrayList<UserContact> uc = new ArrayList<>();
                    uc.add(new UserContact(user, contactDao.findByEmail(dto.getContact().getEmail()), dto.getRelation()));
                    userContactDao.save(uc);
                }else{
                    list = (List)userContactDao.findAll();
                    ArrayList<UserContact> uc = new ArrayList<>();
                    for(int i=0;i<list.size() && ok;i++){
                        if(list.get(i).getContact_id().getPhone() == dto.getContact().getPhone())
                            ok = false;
                    }
                    if(ok){
                        uc.add(new UserContact(user, contactDao.findByPhoneAndDniAndEmail(dto.getContact().getPhone(), dto.getContact().getDni(), dto.getContact().getEmail()), dto.getRelation()));
                        userContactDao.save(uc);
                    }else{
                        throw new ContactAlreadyExists("");
                    }
                }
            }
            return new SuccessfulAction("200", "Contact created succesfully.");
        }catch (ContactAlreadyExists e){
            throw  new ContactAlreadyExists("There are already a contact with the same email, phone or DNI.");
        }catch (Exception e){
            throw new UserDoesNotExistException("The user does not exists");
        }
    }
    @RequestMapping(value = "/remove", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessfulAction removeContact(@ApiParam(value = "Contact that the user wants to delete." ,required=true )
                                             @RequestBody Contact contact, @PathVariable String id) {
        /*try{
            User user = userDao.findById(id);
            if(user==null)
                throw new UserDoesNotExistException("");
            else{
                userContactDao.deleteUserContactById(new UserContactPK(user.getDni(), contactDao.findByEmail(contact.getEmail()).getId()));

            }
        }catch(UserDoesNotExistException e){
            throw new UserDoesNotExistException("The user does not exists");
        }*/
        return new SuccessfulAction("200", "Contact deleted succesfully.");
    }
    @RequestMapping(value = "/getContacts", method = RequestMethod.GET)
    public SuccessfulAction getContacts(@PathVariable String id) {
        try{
            User user = userDao.findById(id);
            if(user==null)
                throw new UserDoesNotExistException("");
            else{
                List <UserContact> list = (List)userContactDao.findAll();
                List <Object> listreturn = new ArrayList<>();
                for(int i=0; i < list.size();i++){
                    if(list.get(i).getUser() == user)
                        listreturn.add(new UserContact(null,null, list.get(i).getContact_id(), list.get(i).getRelation()));
                }
                return new SuccessfulAction("200", "Data retrivied successfuly.", listreturn);
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
