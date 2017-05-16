package NiUnaMas.Controller;

import NiUnaMas.Api.AdminApiDoc;
import NiUnaMas.Controller.Exceptions.CodeDoesNotExistException;
import NiUnaMas.Controller.Exceptions.NotificationDoesNotExistsException;
import NiUnaMas.Controller.Exceptions.UserDoesNotExistException;
import NiUnaMas.Daos.*;
import NiUnaMas.Models.*;
import NiUnaMas.Varios.Uris;
import NiUnaMas.Varios.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 15/05/2017.
 */

@RestController
@CrossOrigin
@RequestMapping(Uris.SERVLET_MAP+Uris.ADMIN+Uris.ID)
public class AdminController implements AdminApiDoc{

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public SuccessfulAction getAllUsers(@PathVariable String id) throws UserDoesNotExistException {
        UserWeb uw = userWebDao.findById(id);
        List<Object> list;
        if(uw != null){
            list = (List)userDao.findAll();
        }else{
            throw new UserDoesNotExistException("Invalid credentials.");
        }
        return new SuccessfulAction("200", "Data retrivied succesfuly.", list);
    }

    @RequestMapping(value = "/getUser/{userDNI}", method = RequestMethod.GET)
    public SuccessfulAction getUser(@PathVariable String id, @PathVariable String userDNI) throws UserDoesNotExistException {
        UserWeb uw = userWebDao.findById(id);
        if(uw != null){
            User user = userDao.findByDni(userDNI);
            if(user != null){
                return new SuccessfulAction("200", "Data retrivied succesfuly.", user);
            }else{
                throw new UserDoesNotExistException("The user does not exists.");
            }
        }else{
            throw new UserDoesNotExistException("Invalid credentials.");
        }
    }
    @RequestMapping(value = "/getContacts/{userDNI}", method = RequestMethod.GET)
    public SuccessfulAction getContacts(@PathVariable String id, @PathVariable String userDNI) throws UserDoesNotExistException {
        UserWeb uw = userWebDao.findById(id);
        if(uw != null){
            User user = userDao.findByDni(userDNI);
            if(user != null){
                List<UserContact> list = (List)userContactDao.findAll();
                List<Object> listReturn = new ArrayList<>();
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getUser().getDni().equalsIgnoreCase(user.getDni())){
                        listReturn.add((list.get(i)));
                    }
                }
                return new SuccessfulAction("200", "Data retrivied succesfuly.", listReturn);
            }else{
                throw new UserDoesNotExistException("The user does not exists.");
            }
        }else{
            throw new UserDoesNotExistException("Invalid credentials.");
        }
    }

    @RequestMapping(value = "/getNotifications/{userDNI}", method = RequestMethod.GET)
    public SuccessfulAction getNotification(@PathVariable String id, @PathVariable String userDNI) throws UserDoesNotExistException {
        UserWeb uw = userWebDao.findById(id);
        if(uw != null){
            User user = userDao.findByDni(userDNI);
            if(user != null){
                List <Object> list = (List)notificationDao.findByUser(user);
                return new SuccessfulAction("200", "Data sent successfully.", list);
            }else{
                throw new UserDoesNotExistException("The user does not exists.");
            }
        }else{
            throw new UserDoesNotExistException("Invalid credentials.");
        }
    }

    @RequestMapping(value = "/cancelNotification/{userDNI}", method = RequestMethod.PUT)
    public SuccessfulAction cancelNotification(@PathVariable String id, @PathVariable String userDNI,
                                               @RequestBody String note) throws UserDoesNotExistException {
        UserWeb uw = userWebDao.findById(id);
        if(uw != null){
            User user = userDao.findByDni(userDNI);
            if(user != null){
                Notification notification = notificationDao.getByUser(user);
                if(notification == null)
                    throw new NotificationDoesNotExistsException("There is not notification to dimiss.");
                else {
                    notificationHistoryDao.save(new NotificationHistory(notification.getDate(), Utils.getDate(),notification.getType(), notification.getCoordX(),
                            notification.getCoordY(), note, user.getDni(), uw.getDni()));
                    notificationDao.delete(notification);
                    return new SuccessfulAction("200", "Notification canceled successfuly.");
                }
            }else{
                throw new UserDoesNotExistException("The user does not exists.");
            }
        }else{
            throw new UserDoesNotExistException("Invalid credentials.");
        }
    }

    @RequestMapping(value = "/getClosedNotifications/{userDNI}", method = RequestMethod.GET)
    public SuccessfulAction getClosedNotification(@PathVariable String id, @PathVariable String userDNI) throws UserDoesNotExistException {
        UserWeb uw = userWebDao.findById(id);
        if(uw != null){
            User user = userDao.findByDni(userDNI);
            if(user != null) {
                return new SuccessfulAction("200", "Data sent successfully.", notificationHistoryDao.findNotificationHistoryByApp(userDNI));
            }else{
                throw new UserDoesNotExistException("The user does not exists.");
            }
        }else{
            throw new UserDoesNotExistException("Invalid credentials.");
        }
    }

    @RequestMapping(value = "/getActiveNotifications", method = RequestMethod.GET)
    public SuccessfulAction getActiveNotifications(@PathVariable String id) throws UserDoesNotExistException {
        UserWeb uw = userWebDao.findById(id);
        if(uw != null){
            return new SuccessfulAction("200", "Data sent successfully.", notificationDao.findAllByOrderByDateDesc());
        }else{
            throw new UserDoesNotExistException("Invalid credentials.");
        }
    }


    @Autowired
    NotificationHistoryDao notificationHistoryDao;
    @Autowired
    NotificationDao notificationDao;
    @Autowired
    UserContactDao userContactDao;
    @Autowired
    UserWebDao userWebDao;
    @Autowired
    UserDao userDao;
}
