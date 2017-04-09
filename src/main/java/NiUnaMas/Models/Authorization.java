package NiUnaMas.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Robert on 08/04/2017.
 */
@Entity
@Table(name = "Authorization")
public class Authorization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String role;

    public Authorization(){

    }

    public Authorization(String role){
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
