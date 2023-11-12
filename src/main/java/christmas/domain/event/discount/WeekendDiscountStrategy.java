package christmas.domain.event.discount;

import christmas.domain.Category;
import christmas.domain.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendDiscountStrategy extends DecemberDiscountStrategy {
    private static final int DISCOUNT_PER_MAIN = 2023;

    @Override
    public int calculateDiscount(Order order, LocalDate currentDate) {
        if (!isWeekend(currentDate)) {
            return 0;
        }

        int mainQuantity = order.getQuantityByCategory(Category.MAIN);
        return mainQuantity * DISCOUNT_PER_MAIN;
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
