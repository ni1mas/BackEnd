package NiUnaMas.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

import java.util.List;


/**
 * Created by Robert on 04/04/2017.
 */
@Entity
@Table(name = "User")
@ApiModel(value = "User model", description = "Complete data of a model User")
public class User {


    @ApiModelProperty(value = "The id of the user", required = false, hidden = true)
    private String id;
    @NotNull
    @Id
    @Column(unique=true)
    @ApiModelProperty(value = "The dni of the user", required = true, example = "74389280K")
    private String dni;
    @NotNull
    @ApiModelProperty(value = "The name of the user", required = true, example = "Eduardo")
    private String name;
    @NotNull
    @ApiModelProperty(value = "The fname of the user", required = true, example = "Manos Tijeras")
    private String fname;
    @NotNull
    @Column(unique=true)
    @ApiModelProperty(value = "The phone of the user", required = true, example = "669754255")
    private int phone;
    @ApiModelProperty(value = "The phone2 of the user", required = true, example = "616600758")
    private int phone2;
    @NotNull
    @Column(unique=true)
    @ApiModelProperty(value = "The email of the user", required = true, example = "example@email.com")
    private String email;
    @NotNull
    @ApiModelProperty(value = "The address of the user", required = true, example = "Nowhere Street")
    private String address;
    @NotNull
    @ApiModelProperty(value = "The password of the user", required = true, example = "SecretPass")
    private String password;
    @OneToMany(mappedBy = "contact_id")
    @ApiModelProperty(value = "The id of the user", required = false, hidden = true)
    private List<UserContact> contactAssoc;

    public User(){}

    public User(String dni, String name, String fname, int phone, int phone2, String email,
                String address, String password){
        this.dni = dni.toUpperCase();
        this.name = name;
        this.fname = fname;
        this.phone = phone;
        this.phone2 = phone2;
        this.email = email;
        this.address = address;
        this.password = password;
        SHA3.DigestSHA3 sha = new SHA3.Digest512();
        byte[] digest = sha.digest((dni).getBytes());
        this.id = Hex.toHexString(digest);
    }

    public User(User user){
        this.dni = user.getDni().toUpperCase();
        this.name = user.name;
        this.fname = user.fname;
        this.phone = user.phone;
        this.phone2 = user.phone2;
        this.email = user.email;
        this.address = user.address;
        this.password = user.password;
        SHA3.DigestSHA3 sha = new SHA3.Digest512();
        byte[] digest = sha.digest((dni).getBytes());
        this.id = Hex.toHexString(digest);
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setPhone2(int phone2) {
        this.phone2 = phone2;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getFname() {
        return fname;
    }

    public int getPhone() {
        return phone;
    }

    public int getPhone2() {
        return phone2;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() { return password; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public List<UserContact> getContactAssoc() {
        return contactAssoc;
    }

    public void setContactAssoc(List<UserContact> contactAssoc) {
        this.contactAssoc = contactAssoc;
    }
}
