package NiUnaMas.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Robert on 06/04/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiError {
    private String code;
    private String message;

    public ApiError() { }

    public ApiError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }
}
