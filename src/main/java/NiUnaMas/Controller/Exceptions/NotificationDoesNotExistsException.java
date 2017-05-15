package NiUnaMas.Controller.Exceptions;

import org.springframework.validation.Errors;

/**
 * Created by Robert on 15/05/2017.
 */
public class NotificationDoesNotExistsException extends RuntimeException{
    private Errors errors;

    public NotificationDoesNotExistsException(String message) {
        super(message);
    }
}
