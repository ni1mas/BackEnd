package NiUnaMas.Models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Robert on 11/05/2017.
 */
@Embeddable
@ApiModel(value = "LocationPK model", description = "Complete data of a model LocationPK")
public class LocationPK implements Serializable {
    @Column(name = "USER_DNI")
    @ApiModelProperty(value = "User that sent the signal of the keep alive.", hidden = true ,required = false, example = "74384560T")
    private String user_dni;

    @Column(name = "Date")
    @ApiModelProperty(value = "Date  of the signal of the keep alive.", required = false, example = "2017-05-11T14:52:36.251Z")
    private Date date;

    public LocationPK(){}

    public LocationPK(Date date, String user_dni){
        this.user_dni = user_dni;
        this.date = date;
    }

    public String getUser_dni() {
        return user_dni;
    }

    public void setUser_dni(String user_dni) {
        this.user_dni = user_dni;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
