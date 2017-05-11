package NiUnaMas.Controller.Exceptions;

import org.springframework.validation.Errors;

/**
 * Created by Robert on 11/05/2017.
 */
public class ContactAlreadyExists extends RuntimeException{
    private Errors errors;

    public ContactAlreadyExists(String message) {
        super(message);
    }

}
