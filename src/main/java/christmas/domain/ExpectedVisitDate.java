package christmas.domain;

import static christmas.constant.EventConstant.EVENT_MONTH;
import static christmas.constant.EventConstant.EVENT_YEAR;

import christmas.exception.ExpectedVisitDateException;
import java.time.DateTimeException;
import java.time.LocalDate;

public class ExpectedVisitDate {
    private final LocalDate date;

    private ExpectedVisitDate(LocalDate date) {
        this.date = date;
    }

    public static ExpectedVisitDate of(int day) {
        try {
            LocalDate date = LocalDate.of(EVENT_YEAR, EVENT_MONTH, day);
            return new ExpectedVisitDate(date);
        } catch (DateTimeException ex) {
            throw new ExpectedVisitDateException();
        }
    }

    public LocalDate getDate() {
        return date;
    }
}
