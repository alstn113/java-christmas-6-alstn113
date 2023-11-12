package christmas.exception;

public enum ErrorMessage {
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_VISIT_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INPUT_IS_EMPTY("입력값은 비어있을 수 없습니다."),
    INPUT_NOT_A_NUMBER("입력값은 숫자여야 합니다.");
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
