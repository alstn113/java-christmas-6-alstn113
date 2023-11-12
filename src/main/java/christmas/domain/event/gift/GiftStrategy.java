package christmas.domain.event.gift;

import christmas.domain.Order;
import christmas.domain.event.EventResult;
import christmas.domain.event.EventStrategy;
import java.time.LocalDate;

public abstract class GiftStrategy extends EventStrategy {
    protected GiftStrategy(LocalDate startDate, LocalDate endDate) {
        super(startDate, endDate);
    }

    @Override
    public EventResult applyEvent(LocalDate currentDate, Order order) {
        if (isApplicable(currentDate, order)) {
            return applyGift(currentDate, order);
        }
        return EventResult.empty();
    }

    public abstract EventResult applyGift(LocalDate currentDate, Order order);
}
