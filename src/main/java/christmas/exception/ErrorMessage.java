package christmas.exception;

public enum ErrorMessage {
    INPUT_NOT_A_NUMBER("입력값은 숫자여야 합니다."),
    INPUT_NOT_EMPTY("입력값은 비어 있을 수 없습니디ㅏ."),
    MENU_NOT_FOUND("존재하지 않는 메뉴입니다."),
    INVALID_EXPECTED_VISIT_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_ORDER_QUANTITY("주문하실 메뉴의 개수는 1개 이상만 가능합니다."),
    DUPLICATED_MENU("주문에 중복된 메뉴가 있습니다."),
    ORDER_OVER_QUANTITY("최대 주문량을 넘었습니다."),
    ORDER_NOT_ONLY_DRINK("음료만 주문할 수 없습니다.");
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
