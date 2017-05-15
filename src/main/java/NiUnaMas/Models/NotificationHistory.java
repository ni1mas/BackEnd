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
    private String notes;
    private String user_app;
    private String user_web;


    public NotificationHistory(){ }

    public NotificationHistory(Date created, Date archived, int type, double coordX, double coordY, String note, String user_app, String user_web){
        this.archived = archived;
        this.coordX = coordX;
        this.coordY = coordY;
        this.created = created;
        this.type = type;
        this.notes = note;
        this.user_app = user_app;
        this.user_web = user_web;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getUser() { return user_web; }

    public void setUser(String user) { this.user_web = user; }

    public String getUserWeb() { return user_web; }

    public void setUserWeb(String userWeb) { this.user_web = userWeb; }
}
