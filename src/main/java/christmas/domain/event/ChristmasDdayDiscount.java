package christmas.domain.event;

import christmas.domain.ExpectedVisitDate;
import christmas.domain.Order;

public class ChristmasDdayDiscount extends ChristmasEvent {
    private static final int DISCOUNT_PER_DAY = 100;
    private static final int DEFAULT_DISCOUNT = 1000;

    @Override
    public EventBenefit applyEvent(ExpectedVisitDate date, Order order) {
        int daysFromEventStart = ChristmasEvent.EVENT_START_DATE.until(date.getDate()).getDays();
        int discount = DEFAULT_DISCOUNT + DISCOUNT_PER_DAY * daysFromEventStart;
        return new EventBenefit(discount);
    }
}
