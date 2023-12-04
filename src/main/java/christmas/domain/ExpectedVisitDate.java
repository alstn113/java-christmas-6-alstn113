package christmas.domain;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import java.time.LocalDate;

public class ExpectedVisitDate {
    private static final int CURRENT_YEAR = 2023;
    private static final int CURRENT_MONTH = 12;
    private static final int MINIMUM_DATE = 1;
    private static final int MAXIMUM_DATE = 31;

    private final LocalDate date;

    public ExpectedVisitDate(int date) {
        validateDate(date);
        this.date = LocalDate.of(CURRENT_YEAR, CURRENT_MONTH, date);
    }

    private void validateDate(int date) {
        if (date < MINIMUM_DATE || date > MAXIMUM_DATE) {
            throw new InvalidInputException(ErrorMessage.INVALID_DATE);
        }
    }

    public LocalDate getDate() {
        return date;
    }
}
