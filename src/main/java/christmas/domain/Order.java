package christmas.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems) {
        this.orderItems = new ArrayList<>(orderItems);
    }

    public int totalPrice() {
        return orderItems.stream()
                .mapToInt(OrderItem::price)
                .sum();
    }
}
