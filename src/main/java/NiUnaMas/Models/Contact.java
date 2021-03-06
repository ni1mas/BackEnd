package NiUnaMas.Models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

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
    @ApiModelProperty(value = "The id that allows to use the api.", hidden = true)
    private String idAccess;
    @Column(unique=true)
    @ApiModelProperty(value = "The dni of the contact", required = true, example = "74589632G")
    private String dni;
    @NotNull
    @ApiModelProperty(value = "The name of the contact", required = true, example = "Pepe")
    private String name;
    @ApiModelProperty(value = "The final name of the contact", required = true, example = "Severo")
    private String fname;
    @NotNull
    @ApiModelProperty(value = "The phone of the contact", required = true, example = "668957423")
    @Column(unique=true)
    private int phone;
    @ApiModelProperty(value = "The email of the contact", required = true, example = "pepe@severo.com")
    @NotNull
    @Column(unique=true)
    private String email;
    @ApiModelProperty(value = "The address of the contact", required = true, example = "Calle falsa 123")
    private String address;
    @ApiModelProperty(value = "The activationCode of the contact", required = true, hidden = true, example = "065a4")
    private String activationCode;
    @ApiModelProperty(value = "The activation status of the account of the contact", required = true, hidden = true, example = "1")
    private boolean active;
    @ApiModelProperty(value = "The password of the contact.", required = true, example = "secretPass")
    private String password;
    @OneToMany(mappedBy = "user_dni")
    @ApiModelProperty(value = "Users that this Contact belong.", required = false, hidden = true)
    private List<UserContact> userAssoc;

    public Contact (){}

    public Contact(Contact c) {
        this.dni = c.dni.toUpperCase();
        this.name = c.name;
        this.fname = c.fname;
        this.phone = c.phone;
        this.email = c.email;
        this.address = c.address;
    }

    public Contact(int phone, String email, String name){
        this.phone = phone;
        this.email = email;
        this.name = name;
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

    public List<UserContact> getUserAssoc() {
        return userAssoc;
    }

    public void setUserAssoc(List<UserContact> userAssoc) {
        this.userAssoc = userAssoc;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getIdAccess() { return idAccess; }

    public void setIdAccess(String idAccess) { this.idAccess = idAccess; }
}
