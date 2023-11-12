package christmas.domain.event;

import christmas.domain.order.Order;
import java.time.LocalDate;

public abstract class EventStrategy {
    private static final int CONDITION_PRICE = 10_000;
    private final LocalDate startDate;
    private final LocalDate endDate;

    protected EventStrategy(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public abstract EventResult applyEvent(LocalDate currentDate, Order order);

    public boolean isApplicable(LocalDate currentDate, Order order) {
        return isInProgress(currentDate) && isTotalPriceOverCondition(order);
    }

    private boolean isTotalPriceOverCondition(Order order) {
        return order.totalPrice() >= CONDITION_PRICE;
    }

    private boolean isInProgress(LocalDate currentDate) {
        // ex) 1~5일이면 1일, 5일은 포함
        return !currentDate.isBefore(startDate) && !currentDate.isAfter(endDate);
    }
}
