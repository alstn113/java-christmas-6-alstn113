package christmas.dto.response;

import christmas.domain.order.OrderItem;

public record OrderItemResponse(String menuName, int quantity) {
    public static OrderItemResponse from(OrderItem orderItem) {
        String menuName = orderItem.menu().getName();
        int quantity = orderItem.quantity();
        return new OrderItemResponse(menuName, quantity);
    }
}
