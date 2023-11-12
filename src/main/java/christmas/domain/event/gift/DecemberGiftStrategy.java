package christmas.domain.event.gift;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import java.time.LocalDate;

public class DecemberGiftStrategy extends GiftStrategy {
    private static final LocalDate DECEMBER_START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate DECEMBER_END_DATE = LocalDate.of(2023, 12, 31);

    private static final int CONDITION_PRICE = 120_000;

    public DecemberGiftStrategy() {
        super(DECEMBER_START_DATE, DECEMBER_END_DATE);
    }

    @Override
    public OrderItem giveGift(Order order, LocalDate currentDate) {
        if (!isConditionSatisfied(order)) {
            return null;
        }
        return new OrderItem("샴페인", 1);
    }

    private boolean isConditionSatisfied(Order order) {
        return order.totalPrice() >= CONDITION_PRICE;
    }
}
