package christmas.domain;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import java.time.LocalDate;

public class VisitDate {
    private static final int DEFAULT_YEAR = 2023;
    private static final int DEFAULT_MONTH = 12;
    private static final int START_DAY = 1;
    private static final int END_DAY = 31;
    private final LocalDate date;

    public VisitDate(int day) {
        validateDateRange(day);
        this.date = createDate(day);
    }

    private LocalDate createDate(int day) {
        try {
            return LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, day);
        } catch (Exception e) {
            throw new InvalidInputException(ErrorMessage.INVALID_VISIT_DATE);
        }
    }

    private void validateDateRange(int day) {
        if (day < START_DAY || day > END_DAY) {
            throw new InvalidInputException(ErrorMessage.INVALID_VISIT_DATE);
        }
    }

    public LocalDate getDate() {
        return date;
    }
}
