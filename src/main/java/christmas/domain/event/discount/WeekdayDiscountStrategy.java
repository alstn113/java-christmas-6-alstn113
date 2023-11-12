package christmas.domain.event.discount;

import christmas.domain.Category;
import christmas.domain.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayDiscountStrategy extends DecemberDiscountStrategy {
    private static final int DISCOUNT_PER_DESSERT = 2023;

    @Override
    public int calculateDiscount(Order order, LocalDate currentDate) {
        if (!isWeekday(currentDate)) {
            return 0;
        }

        int desertQuantity = order.getQuantityByCategory(Category.DESSERT);
        return desertQuantity * DISCOUNT_PER_DESSERT;
    }


    private boolean isWeekday(LocalDate currentDate) {
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        return dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY;
    }
}
