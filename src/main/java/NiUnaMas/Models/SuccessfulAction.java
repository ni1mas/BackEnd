package NiUnaMas.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Robert on 06/04/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuccessfulAction {
    private String code;
    private String message;
    private List<Object> results;
    public SuccessfulAction() { }

    public SuccessfulAction(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public SuccessfulAction(String code, String message, List<Object> results) {
        this.code = code;
        this.message = message;
        this.results = results;
    }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public List<Object> getResults() {
        return results;
    }

    public void setResults(List<Object> results) {
        this.results = results;
    }

}
