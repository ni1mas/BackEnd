package NiUnaMas.Controller.Exceptions;

import org.springframework.validation.Errors;

/**
 * Created by Robert on 08/04/2017.
 */
public class InvalidTypeException extends RuntimeException{
    private Errors errors;

    public InvalidTypeException(String message) {
        super(message);
    }

}
