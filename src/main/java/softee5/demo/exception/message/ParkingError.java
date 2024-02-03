package softee5.demo.exception.message;

public enum ParkingError implements ErrorMessage{
    NOT_EXIST_PARKING("존재하지 않는 주차장입니다.");

    private final String message;

    ParkingError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
