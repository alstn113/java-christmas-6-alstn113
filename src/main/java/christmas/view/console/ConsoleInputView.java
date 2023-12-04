package christmas.view.console;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import christmas.view.InputView;
import christmas.view.util.InputUtil;

public class ConsoleInputView implements InputView {
    @Override
    public int readExpectedVisitDate() {
        try {
            System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
            String input = Console.readLine();
            return InputUtil.parseInputToInt(input);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(ErrorMessage.INVALID_DATE);
        }
    }

    @Override
    public String readOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        return Console.readLine();
    }
}
