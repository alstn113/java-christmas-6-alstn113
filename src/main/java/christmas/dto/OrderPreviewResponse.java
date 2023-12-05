package christmas.dto;

import christmas.domain.order.Order;
import java.util.LinkedHashMap;
import java.util.Map;

public record OrderPreviewResponse(Map<String, Integer> orderItems, int totalAmount) {
    public static OrderPreviewResponse from(Order order) {
        Map<String, Integer> orderItems = new LinkedHashMap<>();
        order.getOrderItems().forEach(orderItem -> {
            String menuName = orderItem.menu().getViewName();
            int quantity = orderItem.quantity();
            orderItems.put(menuName, quantity);
        });

        return new OrderPreviewResponse(orderItems, order.getTotalPrice());
    }
}
