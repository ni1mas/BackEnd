package NiUnaMas.Controller;

/**
 * Created by Robert on 03/04/2017.
 */


    import NiUnaMas.Models.Notification;
    import NiUnaMas.Daos.NotificationDao;
    import NiUnaMas.Varios.Uris;
    import NiUnaMas.Daos.UserDao;
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
public class NotificationController {

    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    /**
     * /create  --> Create a new notification and save it in the database.
     * @param type 1: Green, 2: Orange, 3: Red
     * @param coordY Notification's email
     * @param coordX Notification's name
     * @return A string describing if the notification is succesfully created or not.
     */

    /*@RequestMapping(value = "/sendNotification", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String create(@PathVariable String id, @RequestBody Notification notification) {
        Notification notification = null;
        try {
            if(type!=1&&type!=2&&type!=3){
                throw new Exception("Invalid type.");
            }
            if(type==1){
                notification = notificationDao.getByUser(userDao.findById(id));
                notification.setType(1);
                notificationDao.save(notification);
            }else {
                notification = new Notification(type, coordX, coordY);
                notification.setUser(userDao.findById(id));
                notificationDao.save(notification);
            }
        }
        catch (Exception ex) {
            return "Error creating the notification: " + ex.toString();
        }
        return "Notification succesfully created! (id = " + notification.getId() + ")";
    }*/

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    @Autowired
    private NotificationDao notificationDao;
    @Autowired
    private UserDao userDao;

} // class NotificationController
