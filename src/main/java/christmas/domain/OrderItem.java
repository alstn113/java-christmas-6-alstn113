package christmas.domain;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;

public record OrderItem(Menu menu, int quantity) {
    private static final int MINIMUM_QUANTITY = 1;

    public OrderItem {
        validateQuantity(quantity);
    }

    private void validateQuantity(int quantity) {
        if (quantity < MINIMUM_QUANTITY) {
            throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
        }
    }

    public int getTotalPrice() {
        return menu.getPrice() * quantity;
    }
}
