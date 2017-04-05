package NiUnaMas.Models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Robert on 05/04/2017.
 */
@Entity
@Table(name = "NotificationHistory")
public class NotificationHistory {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date created;
    private Date archived;
    private int type;
    private double coordX;
    private double coordY;
    private int stateHistory;
    private String notes;
    @JoinColumn(name = "user_dni")
    @ManyToOne
    private User user;
    @JoinColumn(name = "web_dni")
    @ManyToOne
    private UserWeb userWeb;


    public NotificationHistory(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getArchived() {
        return archived;
    }

    public void setArchived(Date archived) {
        this.archived = archived;
    }

    public int getType() {
        return type;
    }

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

    public int getStateHistory() {
        return stateHistory;
    }

    public void setStateHistory(int stateHistory) {
        this.stateHistory = stateHistory;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
