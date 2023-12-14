package christmas.domain.event;

import christmas.domain.OrderItem;
import java.util.Optional;

public class EventBenefit {
    private final int discount;
    private final Optional<OrderItem> gift;

    public EventBenefit(int discount, OrderItem gift) {
        this.discount = discount;
        this.gift = Optional.ofNullable(gift);
    }

    public EventBenefit(int discount) {
        this(discount, null);
    }

    public int getDiscount() {
        return discount;
    }

    public Optional<OrderItem> getGift() {
        return gift;
    }
}
