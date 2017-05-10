package NiUnaMas.Api;

import NiUnaMas.Models.ResponseToContactDTO;
import NiUnaMas.Models.SuccessfulAction;
import NiUnaMas.Models.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Robert on 10/05/2017.
 */
@Api(value = "response", description = "the reponse API")
public interface ResponseApiDoc {
    @ApiOperation(value = "Allow to send news responses.", notes = "The /send contact endpoint allow to send news contacts.",
            response = SuccessfulAction.class, tags={ "Response", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Response sent successfuly.", response = SuccessfulAction.class)})
    @RequestMapping(value = "/response/send",
            produces = { "application/json" },
            method = RequestMethod.POST)
    SuccessfulAction sendResponse(@ApiParam(value = "New quiz done." ,required=true )@RequestBody ResponseToContactDTO dto);
}
