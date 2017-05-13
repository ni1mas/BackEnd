package NiUnaMas.Controller.Exceptions;

import org.springframework.validation.Errors;

/**
 * Created by Robert on 13/05/2017.
 */
public class CodeDoesNotExistException extends RuntimeException {
    private Errors errors;

    public CodeDoesNotExistException(String message) {
        super(message);
    }
}
