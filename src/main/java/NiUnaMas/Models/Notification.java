package NiUnaMas.Models;

/**
 * Created by Robert on 03/04/2017.
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author robert
 */

@Entity
@Table(name = "Notification")
@ApiModel(value = "Notification model", description = "Complete data of a model Notification")
public class Notification {
    /////
    // Private..
    /////
    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    @ApiModelProperty(value = "The id of the user", required = false, hidden = true)
    private User user;
    // Notification's id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "The id of the user", required = false, hidden = true)
    private int id;

    // Notification's type
    @NotNull
    @ApiModelProperty(value = "The dni of the user", required = true, example = "2")
    private int type;

    // Notification's Coordinate X
    @NotNull
    @ApiModelProperty(value = "The dni of the user", required = true, example = "54.2513326")
    private double coordX;

    // Notification's Coordinate Y
    @NotNull
    @ApiModelProperty(value = "The dni of the user", required = true, example = "3.5416541")
    private double coordY;

    @NotNull
    @ApiModelProperty(value = "The date when the notification was sent", required = true, hidden = true, example = "1495036057894")
    private Date date;
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

    public void setCoordX(double coordX) {
        this.coordX = coordX;
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

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }
} // class Notification