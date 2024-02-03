package softee5.demo.exception.message;

public enum HistoryError implements ErrorMessage{
    NOT_EXIST_HISTORY("존재하지 않는 히스토리 입니다.");

    private final String message;

    HistoryError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
