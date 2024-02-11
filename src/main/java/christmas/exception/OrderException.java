package christmas.exception;

public class OrderException extends BaseException {
    public OrderException() {
        super(ErrorMessage.INVALID_ORDER.getMessage());
    }

    public OrderException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
