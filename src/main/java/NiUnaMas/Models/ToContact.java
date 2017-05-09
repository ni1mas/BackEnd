package NiUnaMas.Models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Robert on 09/05/2017.
 */
@Entity
@Table(name = "Response")
@ApiModel(value = "ToContact model", description = "Complete data of a model ToContact")
public class ToContact {
    @Id
    @ApiModelProperty(value = "The phone of the person that did the quiz", required = true, example = "669854851")
    public int phone;
    @ApiModelProperty(value = "The name of the person that did the quiz", required = true, example = "Andrea")
    public String name;

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
