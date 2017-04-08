package NiUnaMas.Controller;

import NiUnaMas.Controller.Exceptions.InvalidCredentialsLoginException;
import NiUnaMas.Controller.Exceptions.UserAlreadyExistException;
import NiUnaMas.Models.SuccessfulAction;
import NiUnaMas.Models.User;
import NiUnaMas.Varios.Uris;
import NiUnaMas.daos.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 04/04/2017.
 */
@RestController
@RequestMapping(Uris.SERVLET_MAP+Uris.USER)
public class UserController {


    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessfulAction login(@RequestBody User user) {
        user = userDao.getUserByEmailAndPassword(user.getEmail(),user.getPassword());

        if(user == null){
            throw new InvalidCredentialsLoginException("Invalid credentials: The email or the password doens't mach");
        }else{
            return new SuccessfulAction("200", "Logged successfully.");
        }
    }

    @Secured("ADMIN")
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessfulAction register(@RequestBody User user) {
        try{
            userDao.save(user);
            List<Object> list = new ArrayList<>();
            list.add(user);
            return new SuccessfulAction("200", "User created successfully.");
        }catch (Exception e){
            throw new UserAlreadyExistException("Invalid user: DNI, phone or email already taken.");
        }


    }


    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    @Autowired
    private UserDao userDao;

}
