package christmas.domain.event;

import christmas.domain.order.Category;
import christmas.domain.order.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayDiscountStrategy extends DecemberEventStrategy {
    private static final int DISCOUNT_PER_DESSERT = 2023;

    @Override
    public EventResult applyEvent(LocalDate currentDate, Order order) {
        int desertQuantity = order.getQuantityByCategory(Category.DESSERT);
        int discountAmount = desertQuantity * DISCOUNT_PER_DESSERT;
        return new EventResult(discountAmount, null);
    }

    @Override
    public boolean isApplicable(LocalDate currentDate, Order order) {
        return super.isApplicable(currentDate, order) && isWeekday(currentDate) && isDesertOrdered(order);
    }

    private boolean isWeekday(LocalDate currentDate) {
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        return dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY;
    }

    private boolean isDesertOrdered(Order order) {
        return order.getQuantityByCategory(Category.DESSERT) > 0;
    }
}
