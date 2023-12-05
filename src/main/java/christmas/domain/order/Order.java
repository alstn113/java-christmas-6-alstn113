package christmas.domain.order;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private static final int MAXIMUM_ORDER_QUANTITY = 20;
    private final List<OrderItem> orderItems;


    public Order(List<OrderItem> orderItems) {
        validate(orderItems);
        this.orderItems = new ArrayList<>(orderItems);
    }

    private void validate(List<OrderItem> orderItems) {
        validateDuplicateMenu(orderItems);
        validateQuantityLimit(orderItems);
        validateOrderOnlyDrink(orderItems);
    }

    private void validateDuplicateMenu(List<OrderItem> orderItems) {
        if (orderItems.size() != orderItems.stream().map(OrderItem::menu).distinct().count()) {
            throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
        }
    }

    private void validateQuantityLimit(List<OrderItem> orderItems) {
        if (orderItems.stream().mapToInt(OrderItem::quantity).sum() > MAXIMUM_ORDER_QUANTITY) {
            throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
        }
    }

    private void validateOrderOnlyDrink(List<OrderItem> orderItems) {
        if (orderItems.stream().allMatch(orderItem ->
                Category.findByMenu(orderItem.menu()) == Category.DRINK)) {
            throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
        }
    }

    public int getTotalPrice() {
        return orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }

    public int getQuantityByCategory(Category category) {
        return orderItems.stream()
                .filter(orderItem -> Category.findByMenu(orderItem.menu()) == category)
                .mapToInt(OrderItem::quantity)
                .sum();

    }

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }
}
