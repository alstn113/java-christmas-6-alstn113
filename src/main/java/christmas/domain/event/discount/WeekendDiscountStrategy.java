package christmas.domain.event.discount;

import christmas.domain.Category;
import christmas.domain.Order;
import christmas.domain.event.EventResult;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendDiscountStrategy extends DecemberDiscountStrategy {
    private static final int DISCOUNT_PER_MAIN = 2023;

    @Override
    public EventResult applyDiscount(LocalDate currentDate, Order order) {
        int mainQuantity = order.getQuantityByCategory(Category.MAIN);
        int discountAmount = mainQuantity * DISCOUNT_PER_MAIN;
        return new EventResult(discountAmount, null);
    }

    @Override
    public boolean isApplicable(LocalDate currentDate, Order order) {
        return super.isApplicable(currentDate, order) && isWeekend(currentDate);
    }

    private boolean isWeekend(LocalDate currentDate) {
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }
}
