package softee5.demo.exception;


import softee5.demo.exception.message.ErrorMessage;

public class NoExistException extends IllegalArgumentException {
    public NoExistException(ErrorMessage message) {
        super(message.getMessage());
    }
}
