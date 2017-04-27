package NiUnaMas.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;


/**
 * Created by Robert on 04/04/2017.
 */
@Entity
@Table(name = "User")
public class User {

    @Id
    private String id;
    @NotNull
    @Column(unique=true)
    private String dni;
    @NotNull
    private String name;
    @NotNull
    private String fname;
    @NotNull
    @Column(unique=true)
    private int phone;
    private int phone2;
    @NotNull
    @Column(unique=true)
    private String email;
    @NotNull
    private String address;
    @NotNull
    private String password;
    public User(){}

    public User(String dni, String name, String fname, int phone, int phone2, String email,
                String address, String password){
        this.dni = dni;
        this.name = name;
        this.fname = fname;
        this.phone = phone;
        this.phone2 = phone2;
        this.email = email;
        this.address = address;
        this.password = password;
        SHA3.DigestSHA3 sha = new SHA3.Digest512();
        byte[] digest = sha.digest((dni+email+phone).getBytes());
        this.id = Hex.toHexString(digest);
    }

    public User(User user){
        this.dni = user.getDni();
        this.name = user.name;
        this.fname = user.fname;
        this.phone = user.phone;
        this.phone2 = user.phone2;
        this.email = user.email;
        this.address = user.address;
        this.password = user.password;
        SHA3.DigestSHA3 sha = new SHA3.Digest512();
        byte[] digest = sha.digest((dni+email+phone).getBytes());
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
}
