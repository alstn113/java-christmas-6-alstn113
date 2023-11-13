package christmas.domain.event;

import christmas.domain.order.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import java.time.LocalDate;

public class GiftEventStrategy extends DecemberEventStrategy {
    private static final int CONDITION_PRICE = 120_000;

    @Override
    public EventResult applyEvent(LocalDate currentDate, Order order) {
        OrderItem gift = new OrderItem(Menu.CHAMPAGNE, 1);
        return new EventResult(0, gift);
    }

    @Override
    public boolean isApplicable(LocalDate currentDate, Order order) {
        return super.isApplicable(currentDate, order) && isConditionSatisfied(order);
    }

    private boolean isConditionSatisfied(Order order) {
        return order.totalPrice() >= CONDITION_PRICE;
    }
}
