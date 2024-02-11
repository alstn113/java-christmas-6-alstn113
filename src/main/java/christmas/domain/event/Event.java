package christmas.domain.event;

import christmas.domain.ExpectedVisitDate;
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

    private boolean isEventProgress(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    public boolean isApplicable(LocalDate date, Order order) {
        return isEventProgress(date) && order.getTotalPrice() >= MIN_ORDER_PRICE_FOR_EVENT;
    }

    public abstract EventBenefit applyEvent(ExpectedVisitDate date, Order order);
}
