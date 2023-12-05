package christmas.domain.event;

import christmas.domain.order.Category;
import christmas.domain.order.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendDiscountEvent extends DecemberEvent {
    private static final int DISCOUNT_PER_MAIN = 2023;

    @Override
    protected EventBenefit applyEvent(LocalDate date, Order order) {
        int mainQuantity = order.getQuantityByCategory(Category.MAIN);
        int discountPrice = mainQuantity * DISCOUNT_PER_MAIN;
        return EventBenefit.of(discountPrice);
    }

    @Override
    public boolean isApplicable(LocalDate visitDate, Order order) {
        return super.isApplicable(visitDate, order) && isWeekdend(visitDate) && isMainOrdered(order);
    }

    private boolean isWeekdend(LocalDate date) {
        // 금요일, 토요일인 경우
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    private boolean isMainOrdered(Order order) {
        return order.getQuantityByCategory(Category.MAIN) > 0;
    }
}
