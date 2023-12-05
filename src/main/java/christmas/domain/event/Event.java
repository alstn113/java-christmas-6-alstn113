package christmas.domain.event;

import christmas.domain.order.Order;
import java.time.LocalDate;

public abstract class Event {
    private static final int MIN_ORDER_PRICE_FOR_EVENT = 10_000;
    private final LocalDate startDate;
    private final LocalDate endDate;

    protected Event(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public EventBenefit applyEventIfApplicable(LocalDate date, Order order) {
        if (isApplicable(date, order)) {
            return applyEvent(date, order);
        }
        return EventBenefit.empty();
    }

    protected abstract EventBenefit applyEvent(LocalDate date, Order order);

    public boolean isApplicable(LocalDate visitDate, Order order) {
        return isEventInProgress(visitDate) && isOrderPriceEnough(order);
    }

    private boolean isEventInProgress(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    private boolean isOrderPriceEnough(Order order) {
        return order.getTotalPrice() >= MIN_ORDER_PRICE_FOR_EVENT;
    }
}
