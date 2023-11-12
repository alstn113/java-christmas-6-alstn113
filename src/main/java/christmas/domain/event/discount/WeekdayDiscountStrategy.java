package christmas.domain.event.discount;

import christmas.domain.Category;
import christmas.domain.Order;
import christmas.domain.event.EventResult;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayDiscountStrategy extends DecemberDiscountStrategy {
    private static final int DISCOUNT_PER_DESSERT = 2023;

    @Override
    public EventResult applyDiscount(LocalDate currentDate, Order order) {
        int desertQuantity = order.getQuantityByCategory(Category.DESSERT);
        int discountAmount = desertQuantity * DISCOUNT_PER_DESSERT;
        return new EventResult(discountAmount, null);
    }

    @Override
    public boolean isApplicable(LocalDate currentDate, Order order) {
        return super.isApplicable(currentDate, order) && isWeekday(currentDate);
    }

    private boolean isWeekday(LocalDate currentDate) {
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        return dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY;
    }
}
