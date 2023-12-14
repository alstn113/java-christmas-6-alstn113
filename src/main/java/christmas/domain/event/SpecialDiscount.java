package christmas.domain.event;

import static christmas.constant.EventConstant.EVENT_MONTH;
import static christmas.constant.EventConstant.EVENT_YEAR;

import christmas.domain.ExpectedVisitDate;
import christmas.domain.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDiscount extends DecemberEvent {
    private static final int SPECIAL_DISCOUNT = 1000;
    private static final LocalDate CHRISTMAS = LocalDate.of(EVENT_YEAR, EVENT_MONTH, 25);

    @Override
    public EventBenefit applyEvent(ExpectedVisitDate date, Order order) {
        return new EventBenefit(SPECIAL_DISCOUNT);
    }

    @Override
    public boolean isApplicable(LocalDate date, Order order) {
        return super.isApplicable(date, order) && isSpecialDay(date);
    }

    private boolean isSpecialDay(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SUNDAY && date == CHRISTMAS;
    }
}
