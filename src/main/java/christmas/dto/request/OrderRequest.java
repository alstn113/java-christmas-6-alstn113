package christmas.dto.request;

import christmas.domain.order.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.exception.OrderException;
import christmas.view.util.InputUtil;
import java.util.List;

public record OrderRequest(String input) {
    private static final String COMMA = ",";
    private static final String DASH = "-";

    public Order toOrder() {
        try {
            List<OrderItem> orderItems = parseOrderItems(input);
            return new Order(orderItems);
        } catch (IllegalArgumentException ex) {
            throw new OrderException();
        }
    }

    private List<OrderItem> parseOrderItems(String input) {
        return InputUtil.parseToList(input, COMMA).stream()
                .map(this::parseOrderItem)
                .toList();
    }

    private OrderItem parseOrderItem(String orderItemFormat) {
        List<String> menuAndQuantity = InputUtil.parseToList(orderItemFormat, DASH);
        if (menuAndQuantity.size() != 2) {
            throw new OrderException();
        }
        String menuName = menuAndQuantity.get(0);
        int quantity = InputUtil.parseToInt(menuAndQuantity.get(1));
        Menu menu = Menu.findByName(menuName);

        return new OrderItem(menu, quantity);
    }
}
