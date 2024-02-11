package christmas.domain.event.december;

import christmas.constant.EventConstant;
import christmas.domain.ExpectedVisitDate;
import christmas.domain.event.EventBenefit;
import christmas.domain.order.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDiscountEvent extends DecemberEvent {
    private static final int SPECIAL_DISCOUNT = 1000;

    @Override
    public EventBenefit applyEvent(ExpectedVisitDate date, Order order) {
        return EventBenefit.of(SPECIAL_DISCOUNT);
    }

    @Override
    public boolean isApplicable(LocalDate date, Order order) {
        return super.isApplicable(date, order) && isSpecialDay(date);
    }

    private boolean isSpecialDay(LocalDate date) {
        // 일요일과 크리스마스인 경우
        return date.getDayOfWeek() == DayOfWeek.SUNDAY || date == EventConstant.CHRISTMAS;
    }
}
