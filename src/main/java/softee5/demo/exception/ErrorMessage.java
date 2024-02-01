package softee5.demo.exception;

public enum ErrorMessage {

    NOT_EXIST_HISTORY("존재하지 않는 히스토리 입니다."),
    NOT_EXIST_PARKING("존재하지 않는 주차장입니다."),
    NOT_EXIST_IMAGE("존재하지 않는 사진입니다."),
    NOT_EXIST_LOCATION("존재하지 않는 위치정보입니다.");



    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
