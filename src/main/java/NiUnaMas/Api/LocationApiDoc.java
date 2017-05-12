package NiUnaMas.Api;

import NiUnaMas.Models.Location;
import NiUnaMas.Models.Notification;
import NiUnaMas.Models.SuccessfulAction;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Robert on 12/05/2017.
 */
@Api(value = "location", description = "the location API")
public interface LocationApiDoc  {
    @ApiOperation(value = "Sends the keepAlive notification", notes = "The keepalive endpoint allow the user to send his last location.", response = SuccessfulAction.class, tags={ "KeepAlvie", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sent successfuly.", response = SuccessfulAction.class),
            @ApiResponse(code = 400, message = "The user does not exists.", response = SuccessfulAction.class) })
    @RequestMapping(value = "/users/{id}/keepalive/send",
            produces = { "application/json" },
            method = RequestMethod.POST)
    SuccessfulAction sendKeepAlive(@ApiParam(value = "The last location the user want to sent." ,required=true ) @RequestBody Location location,
                                      @ApiParam(value = "ID of the user.",required=true ) @PathVariable("id") String id);

}
