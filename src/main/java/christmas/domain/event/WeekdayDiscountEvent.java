package christmas.domain.event;

import christmas.domain.order.Category;
import christmas.domain.order.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayDiscountEvent extends DecemberEvent {
    private static final int DISCOUNT_PRICE_PER_DESSERT = 2023;

    @Override
    protected EventBenefit applyEvent(LocalDate date, Order order) {
        int dessertQuantity = order.getQuantityByCategory(Category.DESSERT);
        int discountPrice = dessertQuantity * DISCOUNT_PRICE_PER_DESSERT;
        return EventBenefit.of(discountPrice);
    }

    @Override
    public boolean isApplicable(LocalDate visitDate, Order order) {
        return super.isApplicable(visitDate, order) && isWeekday(visitDate) && isDessertOrdered(order);
    }

    private boolean isWeekday(LocalDate date) {
        // 금요일, 토요일이 아닌 경우
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY;
    }

    private boolean isDessertOrdered(Order order) {
        return order.getQuantityByCategory(Category.DESSERT) > 0;
    }
}
