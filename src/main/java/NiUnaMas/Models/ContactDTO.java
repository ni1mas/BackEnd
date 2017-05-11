package NiUnaMas.Models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Robert on 11/05/2017.
 */
@ApiModel(value = "ContactDTO ", description = "Complete data of the DTO Contact")
public class ContactDTO {
    @ApiModelProperty(value = "The new contact.", required = true)
    private Contact contact;
    @ApiModelProperty(value = "The relation the victim has with the contact.", required = false)
    private String relation;

    public Contact getContact() { return contact; }

    public void setContact(Contact contact) { this.contact = contact; }

    public String getRelation() { return relation; }

    public void setRelation(String relation) { this.relation = relation; }
}
