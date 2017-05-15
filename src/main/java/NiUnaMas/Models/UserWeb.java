package NiUnaMas.Models;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import sun.security.util.Password;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * Created by Robert on 05/04/2017.
 */
@Entity
@Table(name = "UserWeb")
public class UserWeb {

    @Id
    private String id;
    private String dni;
    private String name;
    private String fname;
    @Column(unique=true)
    private String email;
    @Min(0)
    @Max(3)
    private int puesto;
    @Size(min=6)
    private String pass;

    public UserWeb(){}

    public UserWeb(String email, String dni, String fname, String name, String pass, int puesto){
        this.email = email;
        this.dni = dni;
        this.fname = fname;
        this.name = name;
        this.pass = pass;
        this.puesto = puesto;
        SHA3.DigestSHA3 sha = new SHA3.Digest512();
        byte[] digest = sha.digest((dni).getBytes());
        this.id = Hex.toHexString(digest);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPuesto() {
        return puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
