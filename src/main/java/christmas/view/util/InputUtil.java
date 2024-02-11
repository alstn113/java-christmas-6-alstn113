package christmas.view.util;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class InputUtil {
    private InputUtil() {
    }

    public static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            throw new InvalidInputException(ErrorMessage.INPUT_NOT_A_NUMBER);
        }
    }

    public static List<String> parseToList(String input, String delimiter) {
        if (input.isBlank()) {
            throw new InvalidInputException(ErrorMessage.INPUT_NOT_EMPTY);
        }
        String[] parts = input.split(delimiter, -1);
        return Arrays.stream(parts).map(String::trim).toList();
    }

    public static <T> T retryOnException(Supplier<T> supplier, boolean lineBreak) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
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
