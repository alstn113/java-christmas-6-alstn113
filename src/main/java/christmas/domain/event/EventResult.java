package christmas.domain.event;

import christmas.domain.order.OrderItem;
import java.util.Optional;

public class EventResult {
    private final int discountAmount;
    private final Optional<OrderItem> gift;

    public EventResult(int discountAmount, OrderItem gift) {
        this.discountAmount = discountAmount;
        this.gift = Optional.ofNullable(gift);
    }

    public static EventResult empty() {
        return new EventResult(0, null);
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public Optional<OrderItem> getGift() {
        return gift;
    }
}
