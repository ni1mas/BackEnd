package NiUnaMas.Controller;

import NiUnaMas.Api.UserApiDoc;
import NiUnaMas.Controller.Exceptions.InvalidCredentialsLoginException;
import NiUnaMas.Controller.Exceptions.UserAlreadyExistException;
import NiUnaMas.Models.SuccessfulAction;
import NiUnaMas.Models.User;
import NiUnaMas.Varios.Uris;
import NiUnaMas.Daos.UserDao;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 04/04/2017.
 */
@RestController
@RequestMapping(Uris.SERVLET_MAP+Uris.USER)
public class UserController implements UserApiDoc {
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessfulAction login(@ApiParam(value = "User that wants to login." ,required=true )@RequestBody User user) {
        user = userDao.getUserByEmailAndPassword(user.getEmail(),user.getPassword());
        List<Object> list = new ArrayList<>();
        list.add(user.getId());
        if(user == null){
            throw new InvalidCredentialsLoginException("Invalid credentials: The email or the password does not mach");
        }else{
            return new SuccessfulAction("200", "Logged successfully.", list);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SuccessfulAction register(@ApiParam(value = "User to create." ,required=true )@RequestBody User user) {
        try{
            if(userDao.getUserByEmailAndPassword(user.getEmail(), user.getPassword())!=null){
                throw new Exception("");
            }else{
                userDao.save(new User(user));
                return new SuccessfulAction("200", "User created successfully.");
            }
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
