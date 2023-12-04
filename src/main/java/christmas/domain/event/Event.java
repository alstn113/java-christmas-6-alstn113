package christmas.domain.event;

import java.time.LocalDate;

public abstract class Event {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private static final int MIN_ORDER_PRICE_FOR_EVENT = 10_000;

    protected Event(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isApplicable(LocalDate visitDate, int orderPrice) {
        return isEventInProgress(visitDate) && isOrderPriceEnough(orderPrice);
    }

    private boolean isEventInProgress(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    private boolean isOrderPriceEnough(int orderPrice) {
        return orderPrice >= MIN_ORDER_PRICE_FOR_EVENT;
    }
}
