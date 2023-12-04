package christmas.view.util;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;

public class InputUtil {
    private InputUtil() {
    }

    public static int parseInputToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(ErrorMessage.INPUT_NOT_A_NUMBER);
        }
    }
}
