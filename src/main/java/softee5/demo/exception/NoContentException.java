package softee5.demo.exception;

public class NoContentException extends IllegalArgumentException{
    public NoContentException(ErrorMessage message) {
        super(message.getMessage());
    }
}
