package NiUnaMas.Controller;

/**
 * Created by Robert on 03/04/2017.
 */
    import NiUnaMas.Api.NotificationApiDoc;
    import NiUnaMas.Controller.Exceptions.InvalidTypeException;
    import NiUnaMas.Controller.Exceptions.UserDoesNotExistException;
    import NiUnaMas.Daos.NotificationHistoryDao;
    import NiUnaMas.Models.Notification;
    import NiUnaMas.Daos.NotificationDao;
    import NiUnaMas.Models.NotificationHistory;
    import NiUnaMas.Models.SuccessfulAction;
    import NiUnaMas.Models.User;
    import NiUnaMas.Varios.Uris;
    import NiUnaMas.Daos.UserDao;
    import NiUnaMas.Varios.Utils;
    import io.swagger.annotations.ApiParam;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.MediaType;
    import org.springframework.web.bind.annotation.*;

    import java.text.SimpleDateFormat;
    import java.util.Date;
    import java.util.List;
    import java.util.TimeZone;

/**
 * A class to test interactions with the MySQL database using the NotificationDao class.
 *
 * @author Robert on 04/04/2017
 */
@RestController
@CrossOrigin
@RequestMapping(Uris.SERVLET_MAP+Uris.USER+Uris.ID+Uris.NOTIFICATION)
public class NotificationController implements NotificationApiDoc {

    @RequestMapping(value = "/sendNotification/{date}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessfulAction sendNotification(@ApiParam(value = "Notification sent." ,required=true )@RequestBody Notification notification,
                                             @PathVariable String id, @PathVariable("date") long date) {
        try {
            if(notification.getType()!=1 && notification.getType()!= 2&& notification.getType()!=3){
                throw new Exception("Invalid type.");
            }
            User user = userDao.findById(id);
            if(user == null){
                throw new Exception("User not found.");
            }
            Notification oldNotification = notificationDao.getByUser(user);
            if(notification.getType() == 1){
                if(oldNotification == null || (oldNotification.getType()!=2 && oldNotification.getType()!=3)){
                    throw new Exception("No notifications to cancel.");
                }else{
                    if(oldNotification.getType() == 2) {
                        notificationHistoryDao.save(new NotificationHistory(oldNotification.getDate(), Utils.getDate(),oldNotification.getType(), oldNotification.getCoordX(),
                                oldNotification.getCoordY(), "", user.getDni(), ""));
                        notificationDao.delete(oldNotification);
                    }
                }
            }else{
                if(oldNotification == null) {
                    notification = new Notification(notification.getType(), notification.getCoordX(), notification.getCoordY());
                    notification.setDate(new Date(date));
                    notification.setUser(userDao.findById(id));
                    notificationDao.save(notification);
                }else
                    if((oldNotification.getType() == 2 && notification.getType() == 3)){
                        oldNotification.setType(3);
                        notificationDao.save(oldNotification);
                    }
            }
        }
        catch (Exception ex) {
            throw  new InvalidTypeException("Error creating the notification: " + ex.toString());
        }
        return new SuccessfulAction("200","Notification created successfully.");
    }

    @RequestMapping(value = "/getNotification", method = RequestMethod.GET)
    public SuccessfulAction getNotification(@PathVariable String id) {
        try {
            User user = userDao.findById(id);
            if(user==null)
                throw new UserDoesNotExistException("");
            else {
                List <Object> list = (List)notificationDao.findByUser(user);
                return new SuccessfulAction("200", "Data sent successfully.", list);
            }

        }catch(UserDoesNotExistException e){
            throw new UserDoesNotExistException("The user does not exists");
        }
    }
    // ------------------------
    // PRIVATE FIELDS
    // ------------------------
    @Autowired
    private NotificationHistoryDao notificationHistoryDao;
    @Autowired
    private NotificationDao notificationDao;
    @Autowired
    private UserDao userDao;

} // class NotificationController
