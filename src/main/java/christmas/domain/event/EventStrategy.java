package christmas.domain.event;

import christmas.domain.order.Order;
import java.time.LocalDate;

public abstract class EventStrategy {
    private static final int MINIMUM_ORDER_PRICE_FOR_EVENT = 10_000;
    private final LocalDate startDate;
    private final LocalDate endDate;

    protected EventStrategy(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public EventResult applyEventIfApplicable(LocalDate currentDate, Order order) {
        if (isApplicable(currentDate, order)) {
            return applyEvent(currentDate, order);
        }
        return EventResult.empty();
    }

    public boolean isApplicable(LocalDate currentDate, Order order) {
        return isInProgress(currentDate) && isTotalPriceOverCondition(order);
    }

    protected abstract EventResult applyEvent(LocalDate currentDate, Order order);

    private boolean isTotalPriceOverCondition(Order order) {
        return order.totalPrice() >= MINIMUM_ORDER_PRICE_FOR_EVENT;
    }

    private boolean isInProgress(LocalDate currentDate) {
        // ex) 1~5일이면 1일, 5일은 포함
        return !currentDate.isBefore(startDate) && !currentDate.isAfter(endDate);
    }
}
