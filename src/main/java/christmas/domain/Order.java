package christmas.domain;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Order {
    private static final int MAX_QUANTITY = 20;

    private final List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems) {
        validate(orderItems);
        this.orderItems = new ArrayList<>(orderItems);
    }

    private void validate(List<OrderItem> orderItems) {
        validateDuplicateMenu(orderItems);
        validateOverQuantity(orderItems);
        validateOrderOnlyDrink(orderItems);
    }

    private void validateDuplicateMenu(List<OrderItem> orderItems) {
        List<Menu> menus = orderItems.stream()
                .map(OrderItem::menu)
                .toList();
        if (menus.size() != new HashSet<>(menus).size()) {
            throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
        }
    }

    private void validateOverQuantity(List<OrderItem> orderItems) {
        int totalQuantity = orderItems.stream()
                .mapToInt(OrderItem::quantity)
                .sum();

        if (totalQuantity > MAX_QUANTITY) {
            throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
        }
    }

    private void validateOrderOnlyDrink(List<OrderItem> orderItems) {
        List<Category> categories = orderItems.stream()
                .map(OrderItem::menu)
                .map(Menu::getCategory)
                .toList();

        if (categories.stream().allMatch(category -> category == Category.DRINK)) {
            throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
        }
    }


    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }
}
