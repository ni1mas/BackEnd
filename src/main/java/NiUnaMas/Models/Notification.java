package NiUnaMas.Models;

/**
 * Created by Robert on 03/04/2017.
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author robert
 */

@Entity
@Table(name = "Notification")
public class Notification {
    /////
    // Private..
    /////
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    // Notification's id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Notification's date
    /*@NotNull
    private Timestamp date;*/

    // Notification's type
    @NotNull
    private int type;

    // Notification's Coordinate X
    @NotNull
    private double coordX;

    // Notification's Coordinate Y
    @NotNull
    private double coordY;

    // Notification's State
    /*@NotNull
    private int state;*/

    /////
    // Public...
    /////
    public Notification() { }

    public Notification(int id) {
        this.id = id;
    }

    public Notification(int type, double coordX, double coordY){
        this.type = type;
        this.coordX = coordX;
        this.coordY = coordY;
    }

    // Getter and setter methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() { return type; }

    public void setType(int type) {
        this.type = type;
    }

    public double getCoordX() {
        return coordX;
    }

    public void setCoordX(double coordY) {
        this.coordY = coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

} // class Notification