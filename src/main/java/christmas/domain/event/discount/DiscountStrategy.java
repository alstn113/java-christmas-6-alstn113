package christmas.domain.event.discount;

import christmas.domain.Order;
import christmas.domain.event.EventResult;
import christmas.domain.event.EventStrategy;
import java.time.LocalDate;

public abstract class DiscountStrategy extends EventStrategy {
    protected DiscountStrategy(LocalDate startDate, LocalDate endDate) {
        super(startDate, endDate);
    }

    @Override
    public EventResult applyEvent(LocalDate currentDate, Order order) {
        if (isApplicable(currentDate, order)) {
            return applyDiscount(currentDate, order);
        }
        return EventResult.empty();
    }

    public abstract EventResult applyDiscount(LocalDate currentDate, Order order);

}
