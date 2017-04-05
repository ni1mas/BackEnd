package NiUnaMas.Controller;

import NiUnaMas.Models.User;
import NiUnaMas.Varios.Uris;
import NiUnaMas.daos.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Robert on 04/04/2017.
 */
@RestController
@RequestMapping(Uris.SERVLET_MAP+Uris.USER)
public class UserController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam("email") String email, @RequestParam("pass") String password,
                         ModelMap model) {
        User user;
        try {
            user = userDao.miFuncion(email, password);
            if(user != null){
                return user.getId();
            }else{
                throw new Exception();
            }
        }
        catch (Exception ex) {
            return "Error 404: User not found";
        }
    }

    @RequestMapping(value = "/create")
    public User register(@RequestParam("dni") String dni, @RequestParam("name") String name, @RequestParam("fname") String fname, @RequestParam("phone") int phone,
                         @RequestParam("phone2") int phone2, @RequestParam("email") String email, @RequestParam("address") String address,
                         @RequestParam("password") String password) {
        User user;
        try {
            user = userDao.save(new User(dni, name, fname, phone, phone2, email, address, password));
        }
        catch (Exception ex) {
            return null;
        }
        return user;
    }


    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    @Autowired
    private UserDao userDao;

}
