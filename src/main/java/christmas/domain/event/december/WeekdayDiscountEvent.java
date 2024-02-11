package christmas.domain.event.december;

import christmas.domain.ExpectedVisitDate;
import christmas.domain.event.EventBenefit;
import christmas.domain.order.Category;
import christmas.domain.order.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayDiscountEvent extends DecemberEvent {
    private static final int DISCOUNT_PER_DESSERT = 2023;

    @Override
    public EventBenefit applyEvent(ExpectedVisitDate date, Order order) {
        int dessertCount = order.getQuantityInCategory(Category.DESSERT);
        int discount = dessertCount * DISCOUNT_PER_DESSERT;
        return EventBenefit.of(discount);
    }

    @Override
    public boolean isApplicable(LocalDate date, Order order) {
        return super.isApplicable(date, order) && isWeekday(date) && isDessertOrdered(order);
    }

    private boolean isWeekday(LocalDate date) {
        // 금요일, 토요일이 아닌 경우 평일
        return date.getDayOfWeek() != DayOfWeek.FRIDAY && date.getDayOfWeek() != DayOfWeek.SATURDAY;
    }

    private boolean isDessertOrdered(Order order) {
        return order.getQuantityInCategory(Category.DESSERT) > 0;
    }
}
