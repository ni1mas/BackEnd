package com.example.Controller;

import com.example.Models.Notification;
import com.example.Models.User;
import com.example.Varios.Uris;
import com.example.daos.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean login(@RequestParam("email") String email, @RequestParam("pass") String password) {
        List<User> list;
        try {
            list = userDao.miFuncion(email, password);
            System.out.print(list);
            if(list.size()>0){
                return new Boolean(true);
            }else{
                throw new Exception();
            }
        }
        catch (Exception ex) {
            return new Boolean(false);
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
