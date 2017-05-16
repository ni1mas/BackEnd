package NiUnaMas.Models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Robert on 15/05/2017.
 */
@ApiModel(value = "Expecific Contact for the add enpoint.", description = "Complete data of a model ContactAdd")

public class ContactsAdd {
    @ApiModelProperty(value = "The activationCode of the contact.", required = true, example = "065a4")
    private String activationCode;
    @ApiModelProperty(value = "The address of the contact.", required = true, example = "Calle falsa 123")
    private String address;
    @ApiModelProperty(value = "The dni of the contact.", required = true, example = "74589632G")
    private String dni;
    @ApiModelProperty(value = "The dni of the contact.", required = true, example = "pepe@severo.com")
    private String email;
    @ApiModelProperty(value = "The final name of the contact.", required = true, example = "Severo")
    private String fname;
    @ApiModelProperty(value = "The name of the contact.", required = true, example = "Pepe")
    private String name;
    @ApiModelProperty(value = "The phone of the contact.", required = true, example = "668957423")
    private int phone;
    @ApiModelProperty(value = "The password of the contact.", required = true, example = "secretPassword")
    private String password;

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

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

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
