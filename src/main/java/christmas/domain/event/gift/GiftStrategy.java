package christmas.domain.event.gift;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.domain.event.EventStrategy;
import java.time.LocalDate;

public abstract class GiftStrategy extends EventStrategy {
    protected GiftStrategy(LocalDate startDate, LocalDate endDate) {
        super(startDate, endDate);
    }

    public OrderItem applyGift(Order order, LocalDate currentDate) {
        if (!isApplicable(currentDate, order)) {
            return null;
        }
        return giveGift(order, currentDate);
    }

    public abstract OrderItem giveGift(Order order, LocalDate currentDate);
}
