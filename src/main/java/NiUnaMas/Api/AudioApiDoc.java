package NiUnaMas.Api;

import NiUnaMas.Models.*;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Robert on 15/05/2017.
 */
@Api(value = "audio", description = "the audio API")
public interface AudioApiDoc {
    @ApiOperation(value = "Allows to send audios.", notes = "The uploadFile endpoint allow the user to send audios.",
            response = SuccessfulAction.class, tags={ "Audio", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sent successfuly.", response = SuccessfulAction.class),
            @ApiResponse(code = 400, message = "The user does not exists", response = ApiError.class) })
    @RequestMapping(value = "/users/{id}/file",
            produces = { "application/json" },
            method = RequestMethod.POST)
    SuccessfulAction uploadFile(@ApiParam(value = "The id of the user that sent the audio." ,required=true ) @RequestParam String id,
                                @ApiParam(value = "The file that the user sent.",required=true ) @RequestParam("file") MultipartFile file);
}
