package NiUnaMas.Api;

import NiUnaMas.Models.Notification;
import NiUnaMas.Models.SuccessfulAction;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Robert on 04/05/2017.
 */
@Api(value = "notification", description = "the users API")
public interface NotificationApiDoc {
    @ApiOperation(value = "Sends a notification", notes = "The notification endpoint allow the user to send three different types of notifications. First one is type 1, that means that you want to cancel your previous notifications. If the system have not recived yet another kind of notification, the systel will return an error. The other two notifications are 2 and 3, orange and red respectively. When the user send them or if he sends type 1 after type 2 or 3 the system will return the reponse with code 200.", response = SuccessfulAction.class, tags={ "Notifications", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Notification created successfully.", response = SuccessfulAction.class),
            @ApiResponse(code = 400, message = "Error creating the notification: (concrete error)", response = SuccessfulAction.class) })
    @RequestMapping(value = "/users/{id}/notifications/sendNotification",
            produces = { "application/json" },
            method = RequestMethod.POST)
    SuccessfulAction sendNotification(@ApiParam(value = "Notification sent." ,required=true ) @RequestBody Notification notification,
                                                                              @ApiParam(value = "ID of the user.",required=true ) @PathVariable("id") String id);
}
