package NiUnaMas.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Robert on 05/04/2017.
 */
@Entity
@Table(name = "Contact")
public class Contact {

    @ManyToOne
    @JoinColumn(name = "user_dni")
    private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String DNI;
    @NotNull
    private String name;
    @NotNull
    private String fname;
    @NotNull
    private int phone;
    private String email;
    @NotNull
    private String address;
    @NotNull
    private String relation;
    @NotNull
    private int priority;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
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

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
