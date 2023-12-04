package christmas.domain.event;

import christmas.domain.order.OrderItem;
import java.util.Optional;

public class EventBenefit {
    private final int discountPrice;
    private final Optional<OrderItem> gift;

    private EventBenefit(int discountPrice, Optional<OrderItem> gift) {
        this.discountPrice = discountPrice;
        this.gift = gift;
    }

    public static EventBenefit of(int discountPrice, OrderItem gift) {
        return new EventBenefit(discountPrice, Optional.ofNullable(gift));
    }

    public static EventBenefit of(int discountPrice) {
        return new EventBenefit(discountPrice, Optional.empty());
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public Optional<OrderItem> getGift() {
        return gift;
    }

}
