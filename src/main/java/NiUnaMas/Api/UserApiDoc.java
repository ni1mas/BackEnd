package NiUnaMas.Api;

import NiUnaMas.Models.ApiError;
import NiUnaMas.Models.Notification;
import NiUnaMas.Models.SuccessfulAction;
import NiUnaMas.Models.User;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-03T22:57:46.089Z")

@Api(value = "users", description = "the users API")
public interface UserApiDoc {

    @ApiOperation(value = "Allows to create new user.", notes = "Allows to create new users in the system. Will return an error if the DNI, email, or phones already exists in the data base.", response = SuccessfulAction.class, tags={ "User", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User created successfully.", response = SuccessfulAction.class),
            @ApiResponse(code = 400, message = "Invalid user: DNI, phone or email already taken.", response = SuccessfulAction.class) })
    @RequestMapping(value = "/users/create",
            produces = { "application/json" },
            method = RequestMethod.POST)
    SuccessfulAction register(@ApiParam(value = "User to create." ,required=true ) @RequestBody User user);


    @ApiOperation(value = "Allow to login in the system", notes = "Sending the correct email and password will return the id of the user that allows to use another services, however if the email and password does not mach it will return an error.", response = SuccessfulAction.class, tags={ "User", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Logged successfully.", response = SuccessfulAction.class),
            @ApiResponse(code = 400, message = "Invalid credentials: The email or the password does not mach", response = SuccessfulAction.class) })
    @RequestMapping(value = "/users/login",
            produces = { "application/json" },
            method = RequestMethod.POST)
    SuccessfulAction login(@ApiParam(value = "User that wants to login." ,required=true ) @RequestBody User user);

}
