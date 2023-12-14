package christmas.domain.event;

import christmas.domain.Category;
import christmas.domain.ExpectedVisitDate;
import christmas.domain.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayDiscount extends DecemberEvent {
    private static final int DISCOUNT_PER_DESSERT = 2023;

    @Override
    public EventBenefit applyEvent(ExpectedVisitDate date, Order order) {
        int dessertCount = order.getQuantityByCategory(Category.DESSERT);
        int discount = dessertCount * DISCOUNT_PER_DESSERT;
        return new EventBenefit(discount);
    }

    @Override
    public boolean isApplicable(LocalDate date, Order order) {
        return super.isApplicable(date, order) && isWeekday(date);
    }

    private boolean isWeekday(LocalDate date) {
        // 금요일, 토요일이 주말
        return date.getDayOfWeek() != DayOfWeek.FRIDAY && date.getDayOfWeek() != DayOfWeek.SATURDAY;
    }
}
