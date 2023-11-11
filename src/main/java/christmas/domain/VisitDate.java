package christmas.domain;

import christmas.exception.ErrorMesssage;
import christmas.exception.InvalidInputException;

public record VisitDate(int date) {
    public VisitDate {
        validateVisitDate();
    }

    private void validateVisitDate() {
        if (date < 1 || date > 31) {
            throw new InvalidInputException(ErrorMesssage.INVALID_VISIT_DATE);
        }
    }
}