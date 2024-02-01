package softee5.demo.exception;


public class NoExistException extends IllegalArgumentException {
    public NoExistException(ErrorMessage message) {
        super(message.getMessage());
    }
}
