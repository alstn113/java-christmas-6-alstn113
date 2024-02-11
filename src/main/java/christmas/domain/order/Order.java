package christmas.domain.order;

import christmas.exception.ErrorMessage;
import christmas.exception.OrderException;
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
        validateDuplicateMenu(orderItems);
        validateOverQuantity(orderItems);
        validateOrderNotOnlyDrink(orderItems);
    }

    private void validateDuplicateMenu(List<OrderItem> orderItems) {
        List<Menu> menus = orderItems.stream()
                .map(OrderItem::menu)
                .toList();

        if (menus.size() != menus.stream().distinct().count()) {
            throw new OrderException(ErrorMessage.DUPLICATED_MENU);
        }
    }

    private void validateOverQuantity(List<OrderItem> orderItems) {
        int totalQuantity = orderItems.stream()
                .mapToInt(OrderItem::quantity)
                .sum();

        if (totalQuantity > MAX_QUANTITY) {
            throw new OrderException(ErrorMessage.ORDER_OVER_QUANTITY);
        }
    }

    private void validateOrderNotOnlyDrink(List<OrderItem> orderItems) {
        List<Category> categories = orderItems.stream()
                .map(OrderItem::menu)
                .map(Menu::getCategory)
                .toList();

        if (categories.stream().allMatch(category -> category == Category.DRINK)) {
            throw new OrderException(ErrorMessage.ORDER_NOT_ONLY_DRINK);
        }
    }

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }

    public int getQuantityInCategory(Category category) {
        return orderItems.stream()
                .filter(orderItem -> orderItem.menu().getCategory() == category)
                .mapToInt(OrderItem::quantity)
                .sum();
    }

    public int getTotalPrice() {
        return orderItems.stream()
                .mapToInt(orderItem -> orderItem.menu().getPrice() * orderItem.quantity())
                .sum();
    }
}