package softee5.demo.exception.message;

public enum ImageError implements ErrorMessage {
    NOT_EXIST_IMAGE("존재하지 않는 사진입니다.");
    private final String message;

    ImageError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
