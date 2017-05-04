package NiUnaMas.Controller;

/**
 * Created by Robert on 03/04/2017.
 */
    import NiUnaMas.Api.NotificationApiDoc;
    import NiUnaMas.Controller.Exceptions.InvalidTypeException;
    import NiUnaMas.Models.Notification;
    import NiUnaMas.Daos.NotificationDao;
    import NiUnaMas.Models.SuccessfulAction;
    import NiUnaMas.Varios.Uris;
    import NiUnaMas.Daos.UserDao;
    import io.swagger.annotations.ApiParam;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.MediaType;
    import org.springframework.web.bind.annotation.*;

/**
 * A class to test interactions with the MySQL database using the NotificationDao class.
 *
 * @author Robert on 04/04/2017
 */
@RestController
@RequestMapping(Uris.SERVLET_MAP+Uris.USER+Uris.ID+Uris.NOTIFICATION)
public class NotificationController implements NotificationApiDoc {

    @RequestMapping(value = "/sendNotification", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessfulAction sendNotification(@ApiParam(value = "Notification sent." ,required=true )@RequestBody Notification notification, @PathVariable String id) {
        try {
            if(notification.getType()!=1 && notification.getType()!= 2&& notification.getType()!=3){
                throw new Exception("Invalid type.");
            }
            if(notification.getType()==1){
                Notification oldNotification = notificationDao.getByUser(userDao.findById(id));
                if(oldNotification == null){
                    throw new Exception("User not found.");
                }
                if(oldNotification.getType()!=2 && oldNotification.getType()!=3)
                    throw new Exception("No notifications to cancel.");
                else{

                    oldNotification.setType(1);
                    notificationDao.save(oldNotification);
                }
            }else {
                notification = new Notification(notification.getType(), notification.getCoordX(), notification.getCoordY());
                notification.setUser(userDao.findById(id));
                notificationDao.save(notification);
            }
        }
        catch (Exception ex) {
            throw  new InvalidTypeException("Error creating the notification: " + ex.toString());
        }
        return new SuccessfulAction("200","Notification created successfully.");
    }
    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    @Autowired
    private NotificationDao notificationDao;
    @Autowired
    private UserDao userDao;

} // class NotificationController
