package christmas.domain.event.december;

import christmas.domain.ExpectedVisitDate;
import christmas.domain.event.EventBenefit;
import christmas.domain.order.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import java.time.LocalDate;

public class GiftEvent extends DecemberEvent {
    private static final int MINIMUM_ORDER_PRICE = 120_000;

    @Override
    public EventBenefit applyEvent(ExpectedVisitDate date, Order order) {
        OrderItem gift = new OrderItem(Menu.CHAMPAGNE, 1);
        return EventBenefit.of(0, gift);
    }

    @Override
    public boolean isApplicable(LocalDate date, Order order) {
        return super.isApplicable(date, order) && isOverMinimumOrderPrice(order);
    }

    private boolean isOverMinimumOrderPrice(Order order) {
        return order.getTotalPrice() >= MINIMUM_ORDER_PRICE;
    }
}
