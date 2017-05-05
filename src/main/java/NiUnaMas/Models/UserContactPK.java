package NiUnaMas.Models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Robert on 05/05/2017.
 */
@Embeddable
public class UserContactPK implements Serializable {

    @Column(name = "USER_DNI")
    private String user_dni;

    @Column(name = "CONTACT_ID")
    private int contact_id;

    public String getUser_dni() {
        return user_dni;
    }

    public void setUser_dni(String user_dni) {
        this.user_dni = user_dni;
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }
}
