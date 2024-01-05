package softee5.demo.exception;


public class NoExistException extends IllegalArgumentException {
    public NoExistException(String message) {
        super(message);
    }
}
