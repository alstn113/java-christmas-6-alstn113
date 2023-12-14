package christmas.domain;

import christmas.constant.EventConstant;
import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import java.time.LocalDate;

public class ExpectedVisitDate {
    private static final int MIN_DAY = 1;
    private static final int MAX_DAY = 31;
    private final LocalDate date;

    public ExpectedVisitDate(int day) {
        validateDay(day);
        this.date = LocalDate.of(EventConstant.EVENT_YEAR, EventConstant.EVENT_MONTH, day);
    }

    private void validateDay(int day) {
        if (day < MIN_DAY || day > MAX_DAY) {
            throw new InvalidInputException(ErrorMessage.INVALID_EXPECTED_VISIT_DATE);
        }
    }

    public LocalDate getDate() {
        return date;
    }
}
