package christmas.domain.order;

import christmas.exception.ErrorMessage;
import christmas.exception.OrderException;

public record OrderItem(Menu menu, int quantity) {
    private static final int MIN_QUANTITY = 1;

    public OrderItem {
        validateQuantity(quantity);
    }

    private void validateQuantity(int quantity) {
        if (quantity < MIN_QUANTITY) {
            throw new OrderException(ErrorMessage.INVALID_ORDER_QUANTITY);
        }
    }

    public int getPrice() {
        return menu.getPrice() * quantity;
    }
}
