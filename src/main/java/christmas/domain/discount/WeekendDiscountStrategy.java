package christmas.domain.discount;

import christmas.domain.Category;
import christmas.domain.Order;
import java.time.LocalDate;

public class WeekendDiscountStrategy extends DecemberDiscountStrategy {
    private static final int DISCOUNT_PER_MAIN = 2023;

    @Override
    public int calculateDiscount(Order order, LocalDate currentDate) {
        if (!isApplicable(currentDate)) {
            return 0;
        }
        int mainQuantity = order.getQuantityByCategory(Category.MAIN);
        return mainQuantity * DISCOUNT_PER_MAIN;
    }

    @Override
    public boolean isApplicable(LocalDate currentDate) {
        return super.isApplicable(currentDate) && isWeekend(currentDate);
    }

    private boolean isWeekend(LocalDate currentDate) {
        return currentDate.getDayOfWeek().getValue() > 5;
    }
}
