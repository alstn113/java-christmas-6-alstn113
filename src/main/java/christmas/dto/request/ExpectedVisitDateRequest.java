package christmas.dto.request;

import christmas.domain.ExpectedVisitDate;
import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import christmas.view.util.InputUtil;

public record ExpectedVisitDateRequest(String input) {
    public ExpectedVisitDate toExpectedVisitDate() {
        try {
            int date = InputUtil.parseToInt(input);
            return new ExpectedVisitDate(date);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(ErrorMessage.INVALID_EXPECTED_VISIT_DATE);
        }
    }
}
