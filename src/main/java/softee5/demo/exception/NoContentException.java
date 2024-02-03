package softee5.demo.exception;

import softee5.demo.exception.message.ErrorMessage;

public class NoContentException extends IllegalArgumentException{
    public NoContentException(ErrorMessage message) {
        super(message.getMessage());
    }
}
