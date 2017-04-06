package NiUnaMas.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Robert on 06/04/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuccessfulAction {
    private String code;
    private String message;

    public SuccessfulAction() { }

    public SuccessfulAction(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }
}
