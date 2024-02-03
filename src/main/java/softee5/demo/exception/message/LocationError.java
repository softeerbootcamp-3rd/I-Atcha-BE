package softee5.demo.exception.message;

public enum LocationError implements ErrorMessage {
    NOT_EXIST_LOCATION("존재하지 않는 위치정보입니다.");
    private final String message;

    LocationError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
