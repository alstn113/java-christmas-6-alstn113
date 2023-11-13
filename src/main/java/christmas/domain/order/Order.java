package christmas.domain.order;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private static final int MAX_QUANTITY = 20;
    private final List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems) {
        validate(orderItems);
        this.orderItems = new ArrayList<>(orderItems);
    }

    private void validate(List<OrderItem> orderItems) {
        validateMaxMenuQuantity(orderItems);
        validateDuplicateMenu(orderItems);
        validateOrderNotOnlyDrink(orderItems);
    }

    private void validateOrderNotOnlyDrink(List<OrderItem> orderItems) {
        if (allItemsAreDrinks(orderItems)) {
            throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
        }
    }

    private void validateMaxMenuQuantity(List<OrderItem> orderItems) {
        if (totalQuantity(orderItems) > MAX_QUANTITY) {
            throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
        }
    }

    private void validateDuplicateMenu(List<OrderItem> orderItems) {
        if (orderItems.stream().map(OrderItem::getMenu).distinct().count() != orderItems.size()) {
            throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
        }
    }

    public int totalPrice() {
        return orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }

    private boolean allItemsAreDrinks(List<OrderItem> orderItems) {
        return orderItems.stream()
                .allMatch(orderItem -> orderItem.isSameCategory(Category.DRINK));
    }


    private int totalQuantity(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }

    public int getQuantityByCategory(Category category) {
        return orderItems.stream()
                .filter(orderItem -> orderItem.isSameCategory(category))
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }
}
