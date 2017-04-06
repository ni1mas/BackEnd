package NiUnaMas.Controller.Exceptions;

/**
 * Created by Robert on 06/04/2017.
 */
import org.springframework.validation.Errors;

@SuppressWarnings("serial")
public class UserAlreadyExistException extends RuntimeException {
    private Errors errors;

    public UserAlreadyExistException(String message) {
        super(message);
    }
}