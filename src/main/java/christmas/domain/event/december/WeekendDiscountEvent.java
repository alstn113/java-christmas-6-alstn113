package christmas.domain.event.december;

import christmas.domain.ExpectedVisitDate;
import christmas.domain.event.EventBenefit;
import christmas.domain.order.Category;
import christmas.domain.order.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendDiscountEvent extends DecemberEvent {
    private static final int DISCOUNT_PER_MAIN = 2023;

    @Override
    public EventBenefit applyEvent(ExpectedVisitDate date, Order order) {
        int mainCount = order.getQuantityInCategory(Category.MAIN);
        int discount = mainCount * DISCOUNT_PER_MAIN;
        return EventBenefit.of(discount);
    }

    @Override
    public boolean isApplicable(LocalDate date, Order order) {
        return super.isApplicable(date, order) && isWeekend(date) && isMainOrdered(order);
    }

    private boolean isWeekend(LocalDate date) {
        // 금요일, 토요일인 경우 주말
        return date.getDayOfWeek() == DayOfWeek.FRIDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    private boolean isMainOrdered(Order order) {
        return order.getQuantityInCategory(Category.MAIN) > 0;
    }
}
