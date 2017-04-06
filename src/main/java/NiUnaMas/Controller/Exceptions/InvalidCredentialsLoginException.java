package NiUnaMas.Controller.Exceptions;

import org.springframework.validation.Errors;

/**
 * Created by Robert on 06/04/2017.
 */
@SuppressWarnings("serial")
public class InvalidCredentialsLoginException extends RuntimeException{
    private Errors errors;

    public InvalidCredentialsLoginException(String message) {
        super(message);
    }
}
