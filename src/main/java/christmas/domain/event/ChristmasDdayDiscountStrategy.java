package christmas.domain.event;

import christmas.domain.order.Order;
import java.time.LocalDate;

public class ChristmasDdayDiscountStrategy extends EventStrategy {
    private static final LocalDate EVENT_START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_END_DATE = LocalDate.of(2023, 12, 25);
    private static final int DEFAULT_DISCOUNT = 1000;
    private static final int DISCOUNT_PER_DAY = 100;

    public ChristmasDdayDiscountStrategy() {
        super(EVENT_START_DATE, EVENT_END_DATE);
    }

    @Override
    public EventResult applyEvent(LocalDate currentDate, Order order) {
        int daysFromEventStart = EVENT_START_DATE.until(currentDate).getDays();
        int discountAmount = DEFAULT_DISCOUNT + DISCOUNT_PER_DAY * daysFromEventStart;
        return new EventResult(discountAmount, null);
    }
}
