package christmas.dto.response;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record OrderResponse(Map<String, Integer> orderResponse) {
    public static OrderResponse from(Order order) {
        Map<String, Integer> orderResponse = new LinkedHashMap<>();
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            String menu = orderItem.menu().getName();
            int quantity = orderItem.quantity();
            orderResponse.put(menu, quantity);
        }
        return new OrderResponse(orderResponse);
    }
}
