package christmas.dto.request;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import christmas.view.util.InputUtil;
import java.util.ArrayList;
import java.util.List;

public record OrderRequest(String input) {
    private static final String COMMA = ",";
    private static final String DASH = "-";

    public Order toOrder() {
        try {
            List<OrderItem> orderItems = new ArrayList<>();
            List<String> orderItemFormats = InputUtil.parseToList(input, COMMA);
            for (String orderItemFormat : orderItemFormats) {
                List<String> menuAndQuantity = InputUtil.parseToList(orderItemFormat, DASH);

                if (menuAndQuantity.size() != 2) {
                    throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
                }

                String menuName = menuAndQuantity.get(0);
                int quantity = InputUtil.parseToInt(menuAndQuantity.get(1));
                Menu menu = Menu.findByName(menuName);
                OrderItem orderItem = new OrderItem(menu, quantity);
                orderItems.add(orderItem);
            }
            return new Order(orderItems);
        } catch (InvalidInputException e) {
            throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
        }
    }
}
