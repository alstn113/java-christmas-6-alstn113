package christmas.domain.order;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import christmas.view.util.InputUtil;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.ErrorCollector;

public class OrderParser {
    private static final String ORDER_DELIMITER = ",";
    private static final String ORDER_ITEM_DELIMITER = "-";

    private OrderParser() {
    }

    public static Order parseInputToOrder(String input) {
        String[] orderItemArray = input.split(ORDER_DELIMITER, -1);
        List<OrderItem> orderItems = Arrays.stream(orderItemArray)
                .map(OrderParser::parseInputToOrderItem)
                .toList();
        return new Order(orderItems);
    }

    private static OrderItem parseInputToOrderItem(String input) {
        String[] orderItemArray = input.split(ORDER_ITEM_DELIMITER, -1);
        if (orderItemArray.length != 2) {
            throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
        }
        Menu menu = Menu.findByMenuName(orderItemArray[0]);
        int quantity = InputUtil.parseInputToInt(orderItemArray[1]);
        return new OrderItem(menu, quantity);
    }
}
