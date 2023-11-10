package christmas.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems) {
        validate(orderItems);
        this.orderItems = new ArrayList<>(orderItems);
    }

    private void validate(List<OrderItem> orderItems) {
        validateMaxMenuQuantity(orderItems);
        validateOrderNotOnlyDrink(orderItems);
    }

    private void validateOrderNotOnlyDrink(List<OrderItem> orderItems) {
        if (allItemsAreDrinks(orderItems)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요");
        }
    }

    private void validateMaxMenuQuantity(List<OrderItem> orderItems) {
        if (totalQuantity(orderItems) > 20) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
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
}
