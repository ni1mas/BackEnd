package NiUnaMas.Controller;

import NiUnaMas.Api.ContactApiDoc;
import NiUnaMas.Controller.Exceptions.CodeDoesNotExistException;
import NiUnaMas.Controller.Exceptions.ContactAlreadyExists;
import NiUnaMas.Controller.Exceptions.InvalidCredentialsLoginException;
import NiUnaMas.Controller.Exceptions.UserDoesNotExistException;
import NiUnaMas.Daos.ContactDao;
import NiUnaMas.Daos.UserContactDao;
import NiUnaMas.Daos.UserDao;
import NiUnaMas.Models.*;
import NiUnaMas.Varios.Uris;
import NiUnaMas.Varios.Utils;
import com.mailjet.client.errors.MailjetException;
import io.swagger.annotations.ApiParam;
import org.aspectj.apache.bcel.classfile.Code;
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
@CrossOrigin
@RequestMapping(Uris.SERVLET_MAP+Uris.USER+Uris.ID+Uris.CONTACT)
public class ContactController implements ContactApiDoc{

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessfulAction addContact(@ApiParam(value = "Contact that the user allows to get notifications." ,required=true )
                                             @RequestBody Contact contact, @ApiParam(value = "Id of the user") @PathVariable String id) throws UserDoesNotExistException, CodeDoesNotExistException{
            User user = userDao.findById(id);
            if(user == null)
                throw new UserDoesNotExistException("The user does not exists");
            else{
                /*List<UserContact> list;
                boolean ok = true;
                Contact test = contactDao.findByPhoneAndDniAndEmail(dto.getContact().getPhone(), dto.getContact().getDni(), dto.getContact().getEmail());
                if (test == null) {
                    Contact contact = dto.getContact();
                    contact.setActive(true);
                    contactDao.save(contact);
                    ArrayList<UserContact> uc = new ArrayList<>();
                    uc.add(new UserContact(user, contactDao.findByEmail(dto.getContact().getEmail()), dto.getRelation()));
                    userContactDao.save(uc);
                    try {
                        Utils.sendMail(user, dto.getContact().getEmail());
                    }catch (Exception e){
                        throw new UserDoesNotExistException("Error sending email");
                    }
                } else {
                    list = (List) userContactDao.findAll();
                    ArrayList<UserContact> uc = new ArrayList<>();
                    for (int i = 0; i < list.size() && ok; i++) {
                        if (list.get(i).getContact_id().getPhone() == dto.getContact().getPhone())
                            ok = false;
                    }
                    if (ok) {
                        uc.add(new UserContact(user, contactDao.findByPhoneAndDniAndEmail(dto.getContact().getPhone(), dto.getContact().getDni(), dto.getContact().getEmail()), dto.getRelation()));
                        userContactDao.save(uc);
                        try {
                            Utils.sendMail(user, dto.getContact().getEmail());
                        }catch (Exception e){
                            throw new UserDoesNotExistException("Error sending email");
                        }
                    } else {
                        throw new ContactAlreadyExists("There are already a contact with the same email, phone or DNI.");
                    }
                }*/
                Contact contactAC = contactDao.findByActivationCode(contact.getActivationCode());
                if(contactAC == null){
                    throw new CodeDoesNotExistException("The contact does not exists");
                }else{
                    contactAC.setActive(true);
                    contactAC.setAddress(contact.getAddress());
                    contactAC.setDni(contact.getDni());
                    contactAC.setEmail(contact.getEmail());
                    contactAC.setFname(contact.getFname());
                    contactAC.setName(contact.getName());
                    contactAC.setPhone(contact.getPhone());
                    contactDao.save(contactAC);
                }
            }
            return new SuccessfulAction("200", "Contact created succesfully.");
    }
    @RequestMapping(value = "/remove", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessfulAction removeContact(@ApiParam(value = "Contact that the user wants to delete." ,required=true )
                                             @RequestBody Contact contact, @PathVariable String id) throws UserDoesNotExistException{
        User user = userDao.findById(id);
        if(user==null)
            throw new UserDoesNotExistException("The user does not exists");
        else{
            Contact contactRemove = contactDao.findByPhone(contact.getPhone());
            UserContact uc = userContactDao.findById(new UserContactPK(user.getDni(), contactRemove.getId()));
            //userContactDao.deleteUserContactById(new UserContactPK(user.getDni(), contactDao.findByEmail(contact.getEmail()).getId()));
            userContactDao.delete(uc);
            contactDao.delete(contactRemove);
        }

        return new SuccessfulAction("200", "Contact deleted succesfully.");
    }
    @RequestMapping(value = "/getContacts", method = RequestMethod.GET)
    public SuccessfulAction getContacts(@PathVariable String id) throws  UserDoesNotExistException{
            User user = userDao.findById(id);
            if(user==null)
                throw new UserDoesNotExistException("The user does not exists");
            else{
                List <UserContact> list = (List)userContactDao.findAll();
                List <Object> listreturn = new ArrayList<>();
                for(int i=0; i < list.size();i++){
                    if(list.get(i).getUser() == user)
                        listreturn.add(new UserContact(null,null, list.get(i).getContact_id(), list.get(i).getRelation()));
                }
                return new SuccessfulAction("200", "Data retrivied successfuly.", listreturn);
            }
    }

    @RequestMapping(value = "/addPartialContact", method = RequestMethod.POST)
    public SuccessfulAction addPartialContact(@RequestBody AnyadirContacto anyadirContacto,  @ApiParam(value = "Id of the user") @PathVariable String id)
            throws CodeDoesNotExistException, UserDoesNotExistException{
        User user = userDao.findById(id);
        if(user != null){
            Contact contact = contactDao.findByPhone(anyadirContacto.getPhone());
            if(contact == null){
                Contact partialContact = new Contact(anyadirContacto.getPhone(), anyadirContacto.getEmail(), anyadirContacto.getName());
                partialContact.setActivationCode(Utils.generateCode());
                userContactDao.save(new UserContact(user, contactDao.save(partialContact), anyadirContacto.getRelation()));
            }else{
                throw new ContactAlreadyExists("There are already a contact with the same email or phone.");
            }
            return new SuccessfulAction("200", "Contact created successfuly.");
        }else
            throw new UserDoesNotExistException("The user does not exists");

    }

    @RequestMapping(value = "/verifyCode", method = RequestMethod.POST)
    public SuccessfulAction verifyCode(@RequestBody String code) throws CodeDoesNotExistException{
        Contact contact = contactDao.findByActivationCode(code);
        String message = "";
        boolean response = false;
        if(contact != null){
            if(contact.isActive())
                message = "The account was already activate.";
            else {
                message = "You can process to registrer you account.";
                response = true;
            }
        }else{
            throw new CodeDoesNotExistException("They code does not match.");
        }
        return new SuccessfulAction("200", message, response);
    }

    @Autowired
    UserDao userDao;
    @Autowired
    ContactDao contactDao;
    @Autowired
    UserContactDao userContactDao;
}
