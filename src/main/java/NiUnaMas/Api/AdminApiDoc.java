package NiUnaMas.Api;

import NiUnaMas.Models.ApiError;
import NiUnaMas.Models.SuccessfulAction;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Robert on 15/05/2017.
 */
@Api(value = "admin", description = "the admin API")
public interface AdminApiDoc {
    @ApiOperation(value = "Allows the admins to get all the notifications.", notes = "The getNotifications admin endpoint allow the admins get the notifications the users sent.",
            response = SuccessfulAction.class, tags={ "Admin", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data retrivied succesfuly.", response = SuccessfulAction.class),
            @ApiResponse(code = 400, message = "The user does not exists.", response = ApiError.class),
            @ApiResponse(code = 400, message = "Invalid credentials.", response = ApiError.class)})
    @RequestMapping(value = "/admin/{id}/getNotifications/{userDNI}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    SuccessfulAction getNotification(@ApiParam(value = "The id of the admin." ,required=true ) @PathVariable("id") String id,
                                @ApiParam(value = "The dni of the user.",required=true ) @PathVariable("userDNI") String userDNI);

    @ApiOperation(value = "Allows the admins to get all the information of one user.", notes = "The getNotifications admin endpoint allow the admins get all the information about one expecifidied user.",
            response = SuccessfulAction.class, tags={ "Admin", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data retrivied succesfuly.", response = SuccessfulAction.class),
            @ApiResponse(code = 400, message = "The user does not exists.", response = ApiError.class),
            @ApiResponse(code = 400, message = "Invalid credentials.", response = ApiError.class)})
    @RequestMapping(value = "/admin/{id}/getUser/{userDNI}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    SuccessfulAction getUser(@ApiParam(value = "The id of the admin." ,required=true ) @PathVariable("id") String id,
                                     @ApiParam(value = "The dni of the user.",required=true ) @PathVariable("userDNI") String userDNI);

    @ApiOperation(value = "Allows the admins to get all the users.", notes = "The getAllUsers admin endpoint allow the admins to get all the information about all the users.",
            response = SuccessfulAction.class, tags={ "Admin", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data retrivied succesfuly.", response = SuccessfulAction.class),
            @ApiResponse(code = 400, message = "Invalid credentials.", response = ApiError.class)})
    @RequestMapping(value = "/admin/{id}/getAllUsers",
            produces = { "application/json" },
            method = RequestMethod.GET)
    SuccessfulAction getAllUsers(@ApiParam(value = "The id of the admin." ,required=true ) @PathVariable("id") String id);

    @ApiOperation(value = "Allows the admins to get all the contacts of an expecified user.", notes = "The getNotifications admin endpoint allow the admins get all the contacts of an expecified user with all his information and relationship he/she has with.",
            response = SuccessfulAction.class, tags={ "Admin", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data retrivied succesfuly.", response = SuccessfulAction.class),
            @ApiResponse(code = 400, message = "The user does not exists.", response = ApiError.class),
            @ApiResponse(code = 400, message = "Invalid credentials.", response = ApiError.class)})
    @RequestMapping(value = "/admin/{id}/getContacts/{userDNI}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    SuccessfulAction getContacts(@ApiParam(value = "The id of the admin." ,required=true ) @PathVariable("id") String id,
                                     @ApiParam(value = "The dni of the user.",required=true ) @PathVariable("userDNI") String userDNI);

    @ApiOperation(value = "Allows the admins to get dimiss the notification of an expecified user.", notes = "The cancelNotification admin endpoint allow the admins cancel the notification that the user sent before. If there are not notifications" +
            "the endpoint returns an error.",
            response = SuccessfulAction.class, tags={ "Admin", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data retrivied succesfuly.", response = SuccessfulAction.class),
            @ApiResponse(code = 400, message = "The user does not exists.", response = ApiError.class),
            @ApiResponse(code = 400, message = "There is not notification to dimiss.", response = ApiError.class),
            @ApiResponse(code = 400, message = "Invalid credentials.", response = ApiError.class)})
    @RequestMapping(value = "/admin/{id}/cancelNotification/{userDNI}",
            produces = { "application/json" },
            method = RequestMethod.PUT)
    SuccessfulAction cancelNotification(@ApiParam(value = "The id of the admin." ,required=true ) @PathVariable("id") String id,
                                 @ApiParam(value = "The dni of the user.",required=true ) @PathVariable("userDNI") String userDNI,
                                 @ApiParam(value = "The resolution of the case.",required=true ) @RequestBody String note);

    @ApiOperation(value = "Allows the admins to get active notifications of an expeficied user.", notes = "The /getActiveNotifications admin endpoint allow the admins get all the active notifications of an expeficied user using as id their and admin id and the dni of the user.",
            response = SuccessfulAction.class, tags={ "Admin", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data retrivied succesfuly.", response = SuccessfulAction.class),
            @ApiResponse(code = 400, message = "Invalid credentials.", response = ApiError.class)})
    @RequestMapping(value = "/admin/{id}/getActiveNotifications",
            produces = { "application/json" },
            method = RequestMethod.GET)
    SuccessfulAction getActiveNotifications(@ApiParam(value = "The id of the admin." ,required=true ) @PathVariable String id);

    @ApiOperation(value = "Allows the admins to get all the closed notificatons.", notes = "The /getActiveNotifications admin endpoint allow the admins get all the notifications that the user dimissed or the admins canceled or closed.",
            response = SuccessfulAction.class, tags={ "Admin", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Data retrivied succesfuly.", response = SuccessfulAction.class),
            @ApiResponse(code = 400, message = "The user does not exists.", response = ApiError.class),
            @ApiResponse(code = 400, message = "Invalid credentials.", response = ApiError.class)})
    @RequestMapping(value = "/admin/{id}/getClosedNotifications/{userDNI}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    SuccessfulAction getClosedNotification(@ApiParam(value = "The id of the admin." ,required=true ) @PathVariable("id") String id,
                                 @ApiParam(value = "The dni of the user.",required=true ) @PathVariable("userDNI") String userDNI);
}
