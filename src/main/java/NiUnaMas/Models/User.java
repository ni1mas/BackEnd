package NiUnaMas.Models;

import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Random;


/**
 * Created by Robert on 04/04/2017.
 */
@Entity
@Table(name = "User")
public class User {

    public User(String dni, String name, String fname, int phone, int phone2, String email, String address, String password){
        this.id = generateHashedId();
        this.DNI=dni;
        this.name=name;
        this.fname=fname;
        this.phone=phone;
        this.phone2=phone2;
        this.email=email;
        this.address=address;
        this.password=password;
    }

    public User(){

    }

    @Id
    private String id;
    @Column(unique=true)
    private String DNI;
    @NotNull
    private String name;
    @NotNull
    private String fname;
    @Column(unique=true)
    private int phone;
    private int phone2;
    @Column(unique=true)
    private String email;
    @NotNull
    private String address;
    @NotNull
    private String password;

    public void setDNI(String DNI) {
        this.DNI = DNI;
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

    public String getDNI() {
        return DNI;
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

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String generateHashedId(){
        long random = new Random().nextLong();
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(random);
        String hashed = java.util.Base64.getEncoder().encodeToString(DigestUtils.sha512(buffer.array()));
        hashed = hashed.replaceAll("/", "p");
        return hashed;
    }
}
