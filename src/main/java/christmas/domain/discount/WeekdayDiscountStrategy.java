package christmas.domain.discount;

import christmas.domain.Category;
import christmas.domain.Order;
import christmas.domain.OrderItem;

public class WeekdayDiscountStrategy extends DiscountStrategy {
    private static final int DISCOUNT_PER_DESSERT = 2023;

    public WeekdayDiscountStrategy() {
        super(1, 31);
    }

    @Override
    public int calculateDiscount(Order order) {
        int desertQuantity = order.getOrderItems().stream()
                .filter(orderItem -> orderItem.category() == Category.DESSERT)
                .mapToInt(OrderItem::quantity)
                .sum();
        return desertQuantity * DISCOUNT_PER_DESSERT;
    }

    @Override
    public boolean isApplicable(int currentDate) {
        return super.isApplicable(currentDate) && isWeekday(currentDate);
    }

    private boolean isWeekday(int currentDate) {
        return currentDate % 7 != 2 && currentDate % 7 != 3;
    }
}
