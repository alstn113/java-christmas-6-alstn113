package christmas.dto.request;

import christmas.domain.ExpectedVisitDate;
import christmas.exception.ExpectedVisitDateException;
import christmas.view.util.InputUtil;

public record ExpectedVisitDateRequest(String input) {
    public ExpectedVisitDate toExpectedVisitDate() {
        try {
            int day = InputUtil.parseToInt(input);
            return ExpectedVisitDate.of(day);
        } catch (IllegalArgumentException ex) {
            throw new ExpectedVisitDateException();
        }
    }
}
