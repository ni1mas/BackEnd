package NiUnaMas.Controller.Exceptions;

import org.springframework.validation.Errors;

/**
 * Created by Robert on 14/05/2017.
 */
public class ContactDoesNotExistsException extends RuntimeException{
    private Errors errors;

    public ContactDoesNotExistsException(String message) {
        super(message);
    }
}
