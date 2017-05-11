package NiUnaMas.Models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Robert on 05/05/2017.
 */


@Entity
@Table(name = "UserContacts")
public class UserContact implements Serializable{

    @EmbeddedId
    private UserContactPK id = new UserContactPK();

    @ManyToOne
    @MapsId("user_dni") //This is the name of attr in UserContactPK class
    @JoinColumn(name = "USER_DNI")
    private User user_dni;

    @ManyToOne
    @MapsId("contact_id")
    @JoinColumn(name = "CONTACT_ID")
    private Contact contact_id;

    private String relation;

    public UserContact(){
    }

    public UserContact (User u, Contact c, String relation){
        this.user_dni = u;
        this.contact_id = c;
        this.relation = relation;
    }

    public UserContact (UserContactPK ucPK, User u, Contact c, String relation){
        this.id = ucPK;
        this.user_dni = u;
        this.contact_id = c;
        this.relation = relation;
    }

    public UserContactPK getId() {
        return id;
    }

    public void setId(UserContactPK id) {
        this.id = id;
    }

    public User getUser() {
        return user_dni;
    }

    public void setUser(User user) {
        this.user_dni = user;
    }

    public Contact getContact_id() {
        return contact_id;
    }

    public void setContact_id(Contact contact_id) {
        this.contact_id = contact_id;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
