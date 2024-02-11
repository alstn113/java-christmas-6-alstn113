package christmas.domain.event.christmas;

import christmas.domain.ExpectedVisitDate;
import christmas.domain.event.EventBenefit;
import christmas.domain.order.Order;

public class ChristmasDdayDiscountEvent extends ChristmasEvent {
    private static final int DEFAULT_DISCOUNT = 1000;
    private static final int DISCOUNT_PER_DAY = 100;

    @Override
    public EventBenefit applyEvent(ExpectedVisitDate date, Order order) {
        int daysFromEventStart = ChristmasEvent.EVENT_START_DATE.until(date.getDate()).getDays();
        int discount = DEFAULT_DISCOUNT + DISCOUNT_PER_DAY * daysFromEventStart;
        return EventBenefit.of(discount);
    }
}
