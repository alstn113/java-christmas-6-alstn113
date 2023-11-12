package christmas.domain.event.discount;

import christmas.domain.Order;
import christmas.domain.event.EventStrategy;
import java.time.LocalDate;

public abstract class DiscountStrategy extends EventStrategy {
    protected DiscountStrategy(LocalDate startDate, LocalDate endDate) {
        super(startDate, endDate);
    }

    public int applyDiscount(Order order, LocalDate currentDate) {
        if (!isInProgress(currentDate)) {
            return 0;
        }
        return calculateDiscount(order, currentDate);
    }

    protected abstract int calculateDiscount(Order order, LocalDate currentDate);
}
