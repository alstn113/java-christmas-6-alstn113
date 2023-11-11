package christmas.domain;

import christmas.exception.ErrorMesssage;
import christmas.exception.InvalidInputException;

public record VisitDate(int date) {
    public VisitDate {
        validateVisitDate(date);
    }

    private void validateVisitDate(int date) {
        if (date < 1 || date > 31) {
            throw new InvalidInputException(ErrorMesssage.INVALID_VISIT_DATE);
        }
    }
}