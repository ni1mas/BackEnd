package NiUnaMas.Controller;

import NiUnaMas.Api.ContactApiDoc;
import NiUnaMas.Controller.Exceptions.*;
import NiUnaMas.Daos.*;
import NiUnaMas.Models.*;
import NiUnaMas.Varios.Uris;
import NiUnaMas.Varios.Utils;
import com.mailjet.client.errors.MailjetException;
import io.swagger.annotations.ApiParam;
import org.aspectj.apache.bcel.classfile.Code;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
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
@RequestMapping(Uris.SERVLET_MAP)
public class ContactController implements ContactApiDoc{

    @RequestMapping(value = Uris.CONTACT+"/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessfulAction addContact(@ApiParam(value = "Contact that the user allows to get notifications." ,required=true )
                                             @RequestBody ContactsAdd contact)
            throws CodeDoesNotExistException, ContactAlreadyExists{
            Contact contactAC = contactDao.findByActivationCode(contact.getActivationCode());
            if(contactAC == null){
                throw new CodeDoesNotExistException("The contact does not exists");
            }else{
                Contact comprobacion = contactDao.findByPhoneOrDniOrEmail(contact.getPhone(), contact.getDni(), contact.getEmail());
                if(comprobacion == null){
                    contactAC.setActive(true);
                    contactAC.setAddress(contact.getAddress());
                    contactAC.setDni(contact.getDni());
                    contactAC.setEmail(contact.getEmail());
                    contactAC.setFname(contact.getFname());
                    contactAC.setName(contact.getName());
                    contactAC.setPhone(contact.getPhone());
                    contactAC.setPassword(contact.getPassword());
                    contactAC.setActivationCode("");
                    SHA3.DigestSHA3 sha = new SHA3.Digest512();
                    byte[] digest = sha.digest((contact.getEmail()).getBytes());
                    contactAC.setIdAccess(Hex.toHexString(digest));
                    contactDao.save(contactAC);
                }else{
                    throw new ContactAlreadyExists("There are already a contact with the same phone, dni or email. Please check if you missmatched" +
                            "any information");
                }
            }

            return new SuccessfulAction("200", "Contact created succesfully.", contactAC.getId());
    }
    @RequestMapping(value = Uris.USER+Uris.ID+Uris.CONTACT+"/remove", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessfulAction removeContact(@ApiParam(value = "Contact that the user wants to delete." ,required=true )
                                             @RequestBody Contact contact, @PathVariable String id) throws UserDoesNotExistException{
        User user = userDao.findById(id);
        if(user==null)
            throw new UserDoesNotExistException("The user does not exists");
        else{
            Contact contactRemove = contactDao.findByPhone(contact.getPhone());
            UserContact uc = userContactDao.findById(new UserContactPK(user.getDni(), contactRemove.getId()));
            userContactDao.delete(uc);
            contactDao.delete(contactRemove);
        }

        return new SuccessfulAction("200", "Contact deleted succesfully.");
    }
    @RequestMapping(value = Uris.USER+Uris.ID+Uris.CONTACT+"/getContacts", method = RequestMethod.GET)
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

    @RequestMapping(value = Uris.USER+Uris.ID+Uris.CONTACT+"/addPartialContact", method = RequestMethod.POST)
    public SuccessfulAction addPartialContact(@RequestBody AnyadirContacto anyadirContacto,  @ApiParam(value = "Id of the user") @PathVariable String id)
            throws CodeDoesNotExistException, UserDoesNotExistException{
        User user = userDao.findById(id);
        if(user != null){
            Contact contact = contactDao.findByPhoneAndEmail(anyadirContacto.getPhone(), anyadirContacto.getEmail());
            if(contact == null){
                Contact partialContact = new Contact(anyadirContacto.getPhone(), anyadirContacto.getEmail(), anyadirContacto.getName());
                partialContact.setActivationCode(Utils.generateCode());
                userContactDao.save(new UserContact(user, contactDao.save(partialContact), anyadirContacto.getRelation()));
                try {
                    Utils.sendMail(user, anyadirContacto.getEmail());
                } catch (MailjetException e) {
                    e.printStackTrace();
                }
            }else{
                userContactDao.save(new UserContact(user, contact, anyadirContacto.getRelation()));
            }
            return new SuccessfulAction("200", "Contact created successfuly.");
        }else
            throw new UserDoesNotExistException("The user does not exists");

    }

    @RequestMapping(value = Uris.CONTACT+"/verifyCode", method = RequestMethod.POST)
    public SuccessfulAction verifyCode(@RequestBody String code) throws CodeDoesNotExistException{
        Contact contact = contactDao.findByActivationCode(code);
        if(contact != null){
            return new SuccessfulAction("200", "You can process to registrer you account.", contact);
        }else{
            throw new CodeDoesNotExistException("They code does not match.");
        }

    }

    @RequestMapping(value = Uris.CONTACT+"/loginContact", method = RequestMethod.POST)
    public SuccessfulAction loginContact(@RequestBody Contact contact) throws  ContactDoesNotExistsException{
        Contact exists = contactDao.findByEmailAndPassword(contact.getEmail(), contact.getPassword());
        if(exists == null)
            throw new ContactDoesNotExistsException("The account does not exist");
        else {

            return new SuccessfulAction("200", "Logged successfuly", exists.getIdAccess());
        }
    }

    @RequestMapping(value = Uris.CONTACT+Uris.ID+"/getKeepAlive", method = RequestMethod.POST)
    public SuccessfulAction getKeepAlive(@PathVariable String id) throws  ContactDoesNotExistsException{
        Contact exists = contactDao.findByIdAccess(id);
        if(exists == null)
            throw new ContactDoesNotExistsException("The account does not exist");
        else {
            List<UserContact> uc = userContactDao.find(exists);
            List<String> dnis = new ArrayList<>();
            for(int i=0;i<uc.size();i++){
                dnis.add(uc.get(i).getUser().getDni());
            }
            List<List<Location>> loc = new ArrayList<>();
            for(int i=0;i<dnis.size();i++){
                loc.add(locationDao.getAllByUser_dni(userDao.findByDni(dnis.get(i))));
            }
            return new SuccessfulAction("200", "Logged successfuly", loc);
        }
    }

    @RequestMapping(value = Uris.CONTACT+Uris.ID+"/getNotification", method = RequestMethod.POST)
    public SuccessfulAction getNotification(@PathVariable String id) throws  ContactDoesNotExistsException{
        Contact exists = contactDao.findByIdAccess(id);
        if(exists == null)
            throw new ContactDoesNotExistsException("The account does not exist");
        else {
            List<UserContact> uc = userContactDao.find(exists);
            List<String> dnis = new ArrayList<>();
            List<Notification> notifications = new ArrayList<>();
            for(int i=0;i<uc.size();i++) {
                dnis.add(uc.get(i).getUser().getDni());
                notifications.add(notificationDao.getByUserOrderByDateDesc(userDao.findByDni(dnis.get(i))));
            }
            return new SuccessfulAction("200", "Logged successfuly", notifications);
        }
    }


    @Autowired
    LocationDao locationDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ContactDao contactDao;
    @Autowired
    UserContactDao userContactDao;
    @Autowired
    NotificationDao notificationDao;
}
