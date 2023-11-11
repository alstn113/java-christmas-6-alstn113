package christmas.domain.discount;

import christmas.domain.Category;
import christmas.domain.Order;
import christmas.domain.OrderItem;
import java.time.LocalDate;

public class WeekdayDiscountStrategy extends DiscountStrategy {
    private static final LocalDate START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate END_DATE = LocalDate.of(2023, 12, 31);
    private static final int DISCOUNT_PER_DESSERT = 2023;

    public WeekdayDiscountStrategy() {
        super(START_DATE, END_DATE);
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
    public boolean isApplicable(LocalDate currentDate) {
        return super.isApplicable(currentDate) && isWeekday(currentDate);
    }

    private boolean isWeekday(LocalDate currentDate) {
        return currentDate.getDayOfWeek().getValue() < 6;
    }
}
