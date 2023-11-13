package christmas.domain.event;

import christmas.domain.order.Category;
import christmas.domain.order.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendDiscountStrategy extends DecemberEventStrategy {
    private static final int DISCOUNT_PER_MAIN = 2023;

    @Override
    public EventResult applyEvent(LocalDate currentDate, Order order) {
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
