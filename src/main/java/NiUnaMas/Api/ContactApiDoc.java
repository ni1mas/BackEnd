package NiUnaMas.Api;

import NiUnaMas.Models.*;
import NiUnaMas.Models.Contact;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Robert on 05/05/2017.
 */
@Api(value = "contact", description = "the contact API")
public interface ContactApiDoc {
    @ApiOperation(value = "Allows to add contacts", notes = "The add contact endpoint allow the user create contacts",
            response = SuccessfulAction.class, tags={ "Contact", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The user does not exists.", response = SuccessfulAction.class),
            @ApiResponse(code = 400, message = "The user does not exists.", response = SuccessfulAction.class) })
    @RequestMapping(value = "/users/{id}/contact/add",
            produces = { "application/json" },
            method = RequestMethod.POST)
    SuccessfulAction addContact(@ApiParam(value = "Contact to add." ,required=true ) @RequestBody Contact contact,
                                @ApiParam(value = "ID of the user.",required=true ) @PathVariable("id") String id);

    @ApiOperation(value = "Allows to remove contacts", notes = "The remove contact endpoint allow the user to remove contacts",
            response = SuccessfulAction.class, tags={ "Contact", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The user does not exists.", response = SuccessfulAction.class),
            @ApiResponse(code = 400, message = "The user does not exists.", response = SuccessfulAction.class) })
    @RequestMapping(value = "/users/{id}/contact/remove",
            produces = { "application/json" },
            method = RequestMethod.POST)
    SuccessfulAction removeContact(@ApiParam(value = "Contact to delete." ,required=true ) @RequestBody Contact contact,
                                @ApiParam(value = "ID of the user.",required=true ) @PathVariable("id") String id);
}
