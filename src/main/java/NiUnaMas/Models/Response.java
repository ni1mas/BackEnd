package NiUnaMas.Models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;

/**
 * Created by Robert on 09/05/2017.
 */
@Entity(name = "Response")
@Table(name = "Response")
@ApiModel(value = "Response model", description = "Complete data of a model Response")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value ="", hidden = true, example = "")
    private int id;
    @ApiModelProperty(value = "Sex of the responser.", required = true, example = "74389280K")
    private boolean sex;
    @ApiModelProperty(value = "1º answer", required = true, example = "Sí")
    private int p1;
    @ApiModelProperty(value = "2º answer", required = true, example = "No")
    private int p2;
    @ApiModelProperty(value = "3º answer", required = true, example = "A veces")
    private int p3;
    @ApiModelProperty(value = "4º answer", required = true, example = "Sí")
    private int p4;
    @ApiModelProperty(value = "5º answer", required = true, example = "Sí")
    private int p5;
    @ApiModelProperty(value = "6º answer", required = true, example = "Sí")
    private int p6;
    @ApiModelProperty(value = "7º answer", required = true, example = "No")
    private int p7;
    @ApiModelProperty(value = "8º answer", required = true, example = "No")
    private int p8;
    @ApiModelProperty(value = "9º answer", required = true, example = "Sí")
    private int p9;
    @ApiModelProperty(value = "10º answer", required = true, example = "A veces")
    private int p10;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getP1() {
        return p1;
    }

    public void setP1(int p1) {
        this.p1 = p1;
    }

    public int getP2() {
        return p2;
    }

    public void setP2(int p2) {
        this.p2 = p2;
    }

    public int getP3() {
        return p3;
    }

    public void setP3(int p3) {
        this.p3 = p3;
    }

    public int getP4() {
        return p4;
    }

    public void setP4(int p4) {
        this.p4 = p4;
    }

    public int getP5() {
        return p5;
    }

    public void setP5(int p5) {
        this.p5 = p5;
    }

    public int getP6() {
        return p6;
    }

    public void setP6(int p6) {
        this.p6 = p6;
    }

    public int getP7() {
        return p7;
    }

    public void setP7(int p7) {
        this.p7 = p7;
    }

    public int getP8() {
        return p8;
    }

    public void setP8(int p8) {
        this.p8 = p8;
    }

    public int getP9() {
        return p9;
    }

    public void setP9(int p9) {
        this.p9 = p9;
    }

    public int getP10() {
        return p10;
    }

    public void setP10(int p10) {
        this.p10 = p10;
    }
}
