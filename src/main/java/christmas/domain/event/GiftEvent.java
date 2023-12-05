package christmas.domain.event;

import christmas.domain.order.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import java.time.LocalDate;

public class GiftEvent extends DecemberEvent {
    private static final int ORDER_THRESHOLD = 120_000;

    @Override
    protected EventBenefit applyEvent(LocalDate date, Order order) {
        OrderItem gift = new OrderItem(Menu.CHAMPAGNE, 1);
        return EventBenefit.of(0, gift);
    }

    @Override
    public boolean isApplicable(LocalDate visitDate, Order order) {
        return super.isApplicable(visitDate, order) && isOverOrderThreshold(order);
    }

    private boolean isOverOrderThreshold(Order order) {
        return order.getTotalPrice() >= ORDER_THRESHOLD;
    }
}
