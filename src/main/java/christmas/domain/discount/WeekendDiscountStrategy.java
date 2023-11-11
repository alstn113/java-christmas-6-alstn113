package christmas.domain.discount;

import christmas.domain.Category;
import christmas.domain.Order;
import christmas.domain.OrderItem;
import java.time.LocalDate;

public class WeekendDiscountStrategy extends DiscountStrategy {
    private static final LocalDate START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate END_DATE = LocalDate.of(2023, 12, 31);
    private static final int DISCOUNT_PER_MAIN = 2023;

    public WeekendDiscountStrategy() {
        super(START_DATE, END_DATE);
    }

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
