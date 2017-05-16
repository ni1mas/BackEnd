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
            @ApiResponse(code = 200, message = "Contact created succesfully.", response = SuccessfulAction.class),
            @ApiResponse(code = 400, message = "The user does not exists.", response = ApiError.class) })
    @RequestMapping(value = "/users/{id}/contact/register",
            produces = { "application/json" },
            method = RequestMethod.POST)
    SuccessfulAction addContact(@ApiParam(value = "Contact to add." ,required=true ) @RequestBody ContactsAdd contact);

    @ApiOperation(value = "Allows to remove contacts", notes = "The remove contact endpoint allow the user to remove contacts",
            response = SuccessfulAction.class, tags={ "Contact", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Contact deleted succesfully.", response = SuccessfulAction.class),
            @ApiResponse(code = 400, message = "The user does not exists.", response = ApiError.class) })
    @RequestMapping(value = "/users/{id}/contact/remove",
            produces = { "application/json" },
            method = RequestMethod.POST)
    SuccessfulAction removeContact(@ApiParam(value = "Contact to delete." ,required=true ) @RequestBody Contact contact,
                                @ApiParam(value = "ID of the user.",required=true ) @PathVariable("id") String id);

    @ApiOperation(value = "Allows to get all the contacts from one user", notes = "The getContacts endpoint allow the user to get his contacts",
            response = SuccessfulAction.class, tags={ "Contact", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The user does not exists.", response = SuccessfulAction.class),
            @ApiResponse(code = 400, message = "The user does not exists", response = ApiError.class)})
    @RequestMapping(value = "/users/{id}/contact/getContacts",
            produces = { "application/json" },
            method = RequestMethod.POST)
    SuccessfulAction getContacts(@ApiParam(value = "ID of the user.",required=true ) @PathVariable("id") String id);

    @ApiOperation(value = "Allow to add a contact in the user side.", notes = "Allow to add a contact and wait that contact to finish the registration.",
            response = SuccessfulAction.class, tags={ "Contact", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Contact created successfuly.", response = SuccessfulAction.class),
            @ApiResponse(code = 400, message = "There are already a contact with the same email or phone.", response = ApiError.class)})
    @RequestMapping(value = "/users/{id}/contact/addPartialContact",
            produces = { "application/json" },
            method = RequestMethod.POST)
    SuccessfulAction addPartialContact(@ApiParam(value = "Contact to add partialy." ,required=true ) @RequestBody AnyadirContacto ac,
                                       @ApiParam(value = "ID of the user.",required=true ) @PathVariable("id") String id);

    @ApiOperation(value = "Allow to determinate if the code still unused.", notes = "The getContacts endpoint allow the contact to start a registration.",
            response = SuccessfulAction.class, tags={ "Contact", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "You can process to registrer you account.", response = SuccessfulAction.class),
            @ApiResponse(code = 200, message = "The account was already activate.", response = SuccessfulAction.class),
            @ApiResponse(code = 400, message = "They code does not match.", response = ApiError.class)})
    @RequestMapping(value = "/users/{id}/contact/verifyCode",
            produces = { "application/json" },
            method = RequestMethod.POST)
    SuccessfulAction verifyCode(@RequestBody String code);
}
