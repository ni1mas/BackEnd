package com.example;

/**
 * Created by Robert on 03/04/2017.
 */


    import com.example.User;
    import com.example.UserDao;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.ResponseBody;
    import org.springframework.web.bind.annotation.RestController;

/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 * @author netgloo
 */
@RestController
public class UserController {

    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    /**
     * /create  --> Create a new user and save it in the database.
     *
     * @param pass User's pass
     * @param name User's name
     * @return A string describing if the user is succesfully created or not.
     */
    @RequestMapping("/create")
    public String create(@RequestParam(value="pass", defaultValue="World")String pass, @RequestParam(value="name", defaultValue="World")String name) {
        User user = null;
        try {
            user = new User(pass, name);
            userDao.save(user);
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created! (id = " + user.getId() + ")";
    }

    /**
     * /delete  --> Delete the user having the passed id.
     *
     * @param id The id of the user to delete
     * @return A string describing if the user is succesfully deleted or not.
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam(value="id")long id) {
        try {
            User user = new User(id);
            userDao.delete(user);
        }
        catch (Exception ex) {
            return "Error deleting the user: " + ex.toString();
        }
        return "User succesfully deleted!";
    }

    /**
     * /get-by-pass  --> Return the id for the user having the passed pass.
     *
     * @param pass The pass to search in the database.
     * @return The user id or a message error if the user is not found.
     */
    @RequestMapping("/get-by-pass")
    @ResponseBody
    public String getByPass(@RequestParam(value="pass", defaultValue="World") String pass) {
        String userId;
        try {
            User user = userDao.findByPass(pass);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }

    /**
     * /update  --> Update the pass and the name for the user in the database
     * having the passed id.
     *
     * @param id The id for the user to update.
     * @param pass The new pass.
     * @param name The new name.
     * @return A string describing if the user is succesfully updated or not.
     */
    @RequestMapping("/update")
    public String updateUser(long id, String pass, String name) {
        try {
            User user = userDao.findOne(id);
            user.setPass(pass);
            user.setName(name);
            userDao.save(user);
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    @Autowired
    private UserDao userDao;

} // class UserController
