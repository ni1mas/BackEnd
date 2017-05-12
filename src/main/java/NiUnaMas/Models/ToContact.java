package NiUnaMas.Models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * Created by Robert on 09/05/2017.
 */
@Entity
@Table(name = "ToContact")
@ApiModel(value = "ToContact model", description = "Complete data of a model ToContact")
public class ToContact {
    @Id
    @ApiModelProperty(value = "The phone of the person that did the quiz", required = true, example = "669854851")
    private int phone;
    @ApiModelProperty(value = "The name of the person that did the quiz", required = true, example = "Andrea")
    private String name;

    @OneToOne
    @JoinColumn(name = "response_id", nullable = false)
    @ApiModelProperty(value = "The id of the responser.", required = false, hidden = true)
    private Response response;

    public ToContact(){}
    public ToContact(ToContact tc, Response response){
        this.name = tc.getName();
        this.phone = tc.getPhone();
        this.response = response;
    }

    public int getPhone() { return phone; }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
