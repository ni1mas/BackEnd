package NiUnaMas.Models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Robert on 05/04/2017.
 */
@Entity
@Table(name = "Contact")
@ApiModel(value = "Contact model", description = "Complete data of a model Contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "Id of the contact", required = false, hidden = true)
    private int id;
    @NotNull
    @Column(unique=true)
    private String dni;
    @NotNull
    @ApiModelProperty(value = "The dni of the contact", required = true, example = "Pepe")
    private String name;
    @NotNull
    @ApiModelProperty(value = "The dni of the contact", required = true, example = "Severo")
    private String fname;
    @NotNull
    @ApiModelProperty(value = "The dni of the contact", required = true, example = "668957423")
    @Column(unique=true)
    private int phone;
    @ApiModelProperty(value = "The dni of the contact", required = true, example = "pepe@severo.com")
    @NotNull
    @Column(unique=true)
    private String email;
    @NotNull
    @ApiModelProperty(value = "The dni of the contact", required = true, example = "Calle falsa 123")
    private String address;
    @NotNull
    @ApiModelProperty(value = "The dni of the contact", required = true, example = "1")
    private int priority;
    /*private String activationCode;
    private boolean active;*/
    @OneToMany(mappedBy = "user_dni")
    @ApiModelProperty(value = "Users that this Contact belong.", required = false, hidden = true)
    private List<UserContact> userAssoc;

    public Contact (){}

    public Contact(Contact c) {
        this.dni = c.dni;
        this.name = c.name;
        this.fname = c.fname;
        this.phone = c.phone;
        this.email = c.email;
        this.address = c.address;
        this.priority = c.priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public List<UserContact> getUserAssoc() {
        return userAssoc;
    }

    public void setUserAssoc(List<UserContact> userAssoc) {
        this.userAssoc = userAssoc;
    }
}
