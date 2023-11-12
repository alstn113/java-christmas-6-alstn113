package christmas.domain.event.discount;

import christmas.domain.Order;
import christmas.domain.event.EventResult;
import java.time.LocalDate;

public class ChristmasDdayDiscountStrategy extends DiscountStrategy {
    private static final LocalDate EVENT_START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_END_DATE = LocalDate.of(2023, 12, 25);
    private static final int DEFAULT_DISCOUNT = 1000;
    private static final int DISCOUNT_PER_DAY = 100;

    public ChristmasDdayDiscountStrategy() {
        super(EVENT_START_DATE, EVENT_END_DATE);
    }

    @Override
    public EventResult applyDiscount(LocalDate currentDate, Order order) {
        int daysFromEventStart = EVENT_START_DATE.until(currentDate).getDays();
        int discountAmount = DEFAULT_DISCOUNT + DISCOUNT_PER_DAY * daysFromEventStart;
        return new EventResult(discountAmount, null);
    }
}
