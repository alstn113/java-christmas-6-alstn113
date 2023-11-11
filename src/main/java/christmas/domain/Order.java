package christmas.domain;

import christmas.exception.ErrorMesssage;
import christmas.exception.InvalidInputException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems) {
        validate(orderItems);
        this.orderItems = new ArrayList<>(orderItems);
    }

    private void validate(List<OrderItem> orderItems) {
        validateMaxMenuQuantity(orderItems);
        validteDulicateMenu(orderItems);
        validateOrderNotOnlyDrink(orderItems);
    }

    private void validateOrderNotOnlyDrink(List<OrderItem> orderItems) {
        if (allItemsAreDrinks(orderItems)) {
            throw new InvalidInputException(ErrorMesssage.INVALID_ORDER);
        }
    }

    private void validateMaxMenuQuantity(List<OrderItem> orderItems) {
        if (totalQuantity(orderItems) > 20) {
            throw new InvalidInputException(ErrorMesssage.INVALID_ORDER);
        }
    }

    private void validteDulicateMenu(List<OrderItem> orderItems) {
        if (orderItems.stream().map(OrderItem::menuName).distinct().count() != orderItems.size()) {
            throw new InvalidInputException(ErrorMesssage.INVALID_ORDER);
        }
    }

    public int totalPrice() {
        return orderItems.stream()
                .mapToInt(OrderItem::price)
                .sum();
    }

    private boolean allItemsAreDrinks(List<OrderItem> orderItems) {
        return orderItems.stream()
                .allMatch(orderItem -> orderItem.category() == Category.DRINK);
    }


    private int totalQuantity(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToInt(OrderItem::quantity)
                .sum();
    }

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }
}
