package NiUnaMas.Controller.Exceptions;

import org.springframework.validation.Errors;

/**
 * Created by Robert on 05/05/2017.
 */
public class UserDoesNotExistException extends RuntimeException {
    private Errors errors;

    public UserDoesNotExistException(String message) {
        super(message);
    }
}
