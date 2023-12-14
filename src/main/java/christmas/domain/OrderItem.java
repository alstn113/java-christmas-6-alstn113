package christmas.domain;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;

public record OrderItem(Menu menu, int quantity) {
    private static final int MIN_QUANTITY = 1;

    public OrderItem {
        validateQuantity(quantity);
    }

    private void validateQuantity(int quantity) {
        if (quantity < MIN_QUANTITY) {
            throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
        }
    }
}
