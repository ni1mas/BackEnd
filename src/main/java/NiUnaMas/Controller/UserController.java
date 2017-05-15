package NiUnaMas.Controller;

import NiUnaMas.Api.UserApiDoc;
import NiUnaMas.Controller.Exceptions.InvalidCredentialsLoginException;
import NiUnaMas.Controller.Exceptions.UserAlreadyExistException;
import NiUnaMas.Models.SuccessfulAction;
import NiUnaMas.Models.User;
import NiUnaMas.Varios.Uris;
import NiUnaMas.Daos.UserDao;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 04/04/2017.
 */
@RestController
@CrossOrigin
@RequestMapping(Uris.SERVLET_MAP+Uris.USER)
public class UserController implements UserApiDoc {
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessfulAction login(@ApiParam(value = "User that wants to login." ,required=true )@RequestBody User user) throws InvalidCredentialsLoginException{
        user = userDao.getUserByEmailAndPassword(user.getEmail(), user.getPassword());
        if (user == null) {
            throw new InvalidCredentialsLoginException("Invalid credentials: The email or the password does not mach");
        } else {
            return new SuccessfulAction("200", "Logged successfully.", user.getId());
        }
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessfulAction register(@ApiParam(value = "User to create." ,required=true )@RequestBody User user) throws  UserAlreadyExistException{
        if(userDao.getUserByEmailAndPassword(user.getEmail(), user.getPassword())!=null){
            throw new UserAlreadyExistException("Invalid user: DNI, phone or email already taken.");
        }else{
            user = userDao.save(new User(user));
            return new SuccessfulAction("200", "User created successfully.", user.getId());
        }
    }
    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    @Autowired
    private UserDao userDao;

}
