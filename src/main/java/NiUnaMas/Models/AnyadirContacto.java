package NiUnaMas.Models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Robert on 13/05/2017.
 */
@ApiModel(value = "AnyadirContacto ", description = "Complete data of the DTO partialAdd contact")
public class AnyadirContacto {
    @ApiModelProperty(value = "The name of the contact", required = true, example = "José panadero")
    private  String name;
    @ApiModelProperty(value = "The phone of the contact", required = true, example = "685974325")
    private int phone;
    @ApiModelProperty(value = "The email of the contact", required = true, example = "jose_panaderia@pan.com")
    private String email;
    @ApiModelProperty(value = "The realtion of the contact", required = true, example = "Amigo íntimo")
    private String relation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
