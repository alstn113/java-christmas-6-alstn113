package christmas.domain.discount;

import christmas.domain.Category;
import christmas.domain.Order;
import christmas.domain.OrderItem;
import java.time.LocalDate;

public class WeekendDiscountStrategy extends DecemberDiscountStrategy {
    private static final int DISCOUNT_PER_MAIN = 2023;

    @Override
    public int calculateDiscount(Order order) {
        int mainQuantity = order.getOrderItems().stream()
                .filter(orderItem -> orderItem.category() == Category.MAIN)
                .mapToInt(OrderItem::quantity)
                .sum();
        return mainQuantity * DISCOUNT_PER_MAIN;
    }

    @Override
    public boolean isApplicable(LocalDate currentDate) {
        return super.isApplicable(currentDate) && isWeekend(currentDate);
    }

    private boolean isWeekend(LocalDate currentDate) {
        return currentDate.getDayOfWeek().getValue() > 5;
    }
}
