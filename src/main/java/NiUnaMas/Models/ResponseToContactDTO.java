package NiUnaMas.Models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Robert on 09/05/2017.
 */

@ApiModel(value = "ResponseToContact DTO", description = "Complete data of the DTO ResponseToContact")
public class ResponseToContactDTO {
    @ApiModelProperty(value = "The answers the user did.", required = true)
    private Response resp;
    @ApiModelProperty(value = "Data of the user that did the quiz that want us to contact him.", required = false)
    private ToContact tc;

    public Response getResp() {
        return resp;
    }

    public void setResp(Response resp) {
        this.resp = resp;
    }

    public ToContact getTc() {
        return tc;
    }

    public void setTc(ToContact tc) {
        this.tc = tc;
    }
}
