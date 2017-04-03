package com.example.Controller;

/**
 * Created by Robert on 03/04/2017.
 */


    import com.example.Models.Notification;
    import com.example.daos.NotificationDao;
    import com.example.Varios.Uris;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(value = "/sendNotification", method = RequestMethod.POST)
    public String create(@RequestParam("type")int type, @RequestParam("coordX")double coordX, @RequestParam("coordY") double coordY) {
        Notification notification = null;
        try {
            if(type!=1&&type!=2&&type!=3){
                throw new Exception("Invalid type.");
            }
            notification = new Notification(type, coordX, coordY);
            notificationDao.save(notification);
        }
        catch (Exception ex) {
            return "Error creating the notification: " + ex.toString();
        }
        return "Notification succesfully created! (id = " + notification.getId() + ")";
    }

    /**
     * /api/getAllNotification  --> Return all the information of the Notification expecified by its Id. If it does exists
     * returns null.
     *
     * @param id The id to search in the database.
     * @return The notification or a null if the notification is not found.
     */
    @RequestMapping(value = "/getNotification", method = RequestMethod.GET)
    public Notification getNotification(@RequestParam("id") int id) {
        Notification notification;
        try {
            notification = notificationDao.findById(id);
        }
        catch (Exception ex) {
            return null;
        }
        return notification;
    }

    /**
     * /api/getAllNotification  --> Return all the information of the Notifications. If it does exists
     * returns null.
     *
     * @return The notifications or a null if theres are not notifications.
            */
    @RequestMapping(value = "/getAllNotification", method = RequestMethod.GET)
    public Iterable<Notification> getAllNotification() {
        Iterable<Notification> list;
        try {
            list = notificationDao.findAll();
        }
        catch (Exception ex) {
            return null;
        }
        return list;
    }


    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    @Autowired
    private NotificationDao notificationDao;

} // class NotificationController
