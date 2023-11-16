package christmas.domain.event;

import christmas.domain.order.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDiscountStrategy extends DecemberEventStrategy {
    private static final LocalDate CHRISTMAS = LocalDate.of(2023, 12, 25);
    private static final int SPECIAL_DISCOUNT = 1000;

    @Override
    public EventResult applyEvent(LocalDate currentDate, Order order) {
        return new EventResult(SPECIAL_DISCOUNT, null);
    }

    @Override
    public boolean isApplicable(LocalDate currentDate, Order order) {
        return super.isApplicable(currentDate, order) && isSpecialDay(currentDate);
    }

    private boolean isSpecialDay(LocalDate currentDate) {
        return currentDate.getDayOfWeek() == DayOfWeek.SUNDAY || currentDate.isEqual(CHRISTMAS);
    }
}
