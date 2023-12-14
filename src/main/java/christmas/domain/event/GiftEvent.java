package christmas.domain.event;

import christmas.domain.ExpectedVisitDate;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.OrderItem;
import java.time.LocalDate;

public class GiftEvent extends DecemberEvent {
    private static final int CONDITION_PRICE = 120_000;

    @Override
    public EventBenefit applyEvent(ExpectedVisitDate date, Order order) {
        OrderItem gift = new OrderItem(Menu.CHAMPAGNE, 1);
        return new EventBenefit(0, gift);
    }

    @Override
    public boolean isApplicable(LocalDate date, Order order) {
        int totalPrice = order.getTotalPrice();
        return super.isApplicable(date, order) && isOverConditionPrice(totalPrice);
    }

    private boolean isOverConditionPrice(int totalPrice) {
        return totalPrice >= CONDITION_PRICE;
    }
}
