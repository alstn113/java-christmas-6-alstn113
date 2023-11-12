package christmas.domain;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import java.time.LocalDate;

public class VisitDate {
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private final LocalDate date;

    public VisitDate(int day) {
        validateVisitDate(day);
        this.date = LocalDate.of(YEAR, MONTH, day);
    }

    private void validateVisitDate(int day) {
        try {
            LocalDate.of(YEAR, MONTH, day);
        } catch (Exception e) {
            throw new InvalidInputException(ErrorMessage.INVALID_VISIT_DATE);
        }
    }

    public LocalDate getDate() {
        return date;
    }
}
