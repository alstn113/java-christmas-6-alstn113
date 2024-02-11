package christmas.domain.event;

import christmas.domain.order.OrderItem;
import java.util.Optional;

public class EventBenefit {
    private final int discount;
    private final Optional<OrderItem> gift;

    private EventBenefit(int discount, Optional<OrderItem> gift) {
        this.discount = discount;
        this.gift = gift;
    }

    public static EventBenefit of(int discount, OrderItem gift) {
        return new EventBenefit(discount, Optional.of(gift));
    }

    public static EventBenefit of(int discount) {
        return new EventBenefit(discount, Optional.empty());
    }

    public int getDiscount() {
        return discount;
    }

    public Optional<OrderItem> getGift() {
        return gift;
    }

    public int getGiftPrice() {
        return gift.map(OrderItem::getPrice)
                .orElse(0);
    }
}
