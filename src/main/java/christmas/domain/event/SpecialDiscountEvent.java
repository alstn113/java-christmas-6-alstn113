package christmas.domain.event;

import christmas.domain.order.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDiscountEvent extends DecemberEvent {
    private static final int SPECIAL_DISCOUNT = 1000;
    private static final LocalDate CHRISTMAS_DATE = LocalDate.of(2023, 12, 25);

    @Override
    protected EventBenefit applyEvent(LocalDate date, Order order) {
        return EventBenefit.of(SPECIAL_DISCOUNT);
    }

    @Override
    public boolean isApplicable(LocalDate visitDate, Order order) {
        return super.isApplicable(visitDate, order) && isSpecialDay(visitDate);
    }

    private boolean isSpecialDay(LocalDate date) {
        // 일요일과 크리스마스인 경우
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SUNDAY || date.equals(CHRISTMAS_DATE);
    }
}
