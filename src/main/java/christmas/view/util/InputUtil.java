package christmas.view.util;

import christmas.domain.order.OrderItem;
import christmas.exception.AppException;
import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class InputUtil {
    private static final String COMMA_DELIMITER = ",";
    private static final String DASH_DELIMITER = "-";

    private InputUtil() {
    }

    public static int parseInputToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(ErrorMessage.INPUT_NOT_A_NUMBER);
        }
    }

    public static List<OrderItem> parseOrderItems(String input) {
        try {
            String[] orderItems = input.split(COMMA_DELIMITER, -1);
            return Arrays.stream(orderItems)
                    .map(String::trim)
                    .map(InputUtil::parseOrderItem)
                    .toList();
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
        }
    }

    private static OrderItem parseOrderItem(String item) {
        String[] menuAndQuantity = item.split(DASH_DELIMITER, -1);
        if (menuAndQuantity.length != 2) {
            throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
        }
        String menu = menuAndQuantity[0];
        int quantity = Integer.parseInt(menuAndQuantity[1]);
        return new OrderItem(menu, quantity);
    }

    public static <T> T retryOnException(Supplier<T> supplier, boolean lineBreak) {
        while (true) {
            try {
                return supplier.get();
            } catch (AppException e) {
                System.out.println(e.getMessage());
                if (lineBreak) {
                    System.out.println();
                }
            }
        }
    }

    public static <T> T retryOnException(Supplier<T> supplier) {
        return retryOnException(supplier, false);
    }
}
