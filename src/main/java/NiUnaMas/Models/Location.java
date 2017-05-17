package NiUnaMas.Models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * Created by Robert on 11/05/2017.
 */

@Entity
@Table(name = "Location")
@ApiModel(value = "Location model", description = "Complete data of a model Location")
public class Location {


    @EmbeddedId
    @ApiModelProperty(value = "Id  of the signal of the keep alive.",required = true, hidden = true, dataType = "NiUnaMas.Models.LocationPK")
    private LocationPK id = new LocationPK();

    @ManyToOne
    @MapsId("user_dni")
    @JoinColumn(name = "USER_DNI")
    @ApiModelProperty(value = "Id  of the signal of the keep alive.", hidden = true ,required = false, example = "74384560T")
    private User user_dni;

    @NotNull
    @ApiModelProperty(value = "CoordX  of the signal of the keep alive.", required = true, example = "3.5416541")
    private double coodX;
    @NotNull
    @ApiModelProperty(value = "CoordY  of the signal of the keep alive.", required = true, example = "55.15614")
    private double coodY;


    public double getCoodX() {
        return coodX;
    }

    public void setCoodX(double coodX) {
        this.coodX = coodX;
    }

    public double getCoodY() {
        return coodY;
    }

    public void setCoodY(double coodY) {
        this.coodY = coodY;
    }

    public LocationPK getId() {
        return id;
    }

    public void setId(LocationPK id) {
        this.id = id;
    }

    public User getUser_dni() {
        return user_dni;
    }

    public void setUser_dni(User user_dni) {
        this.user_dni = user_dni;
    }
}
