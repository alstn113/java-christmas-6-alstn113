package christmas.domain.discount;

import christmas.domain.Category;
import christmas.domain.Order;
import java.time.LocalDate;

public class WeekdayDiscountStrategy extends DecemberDiscountStrategy {
    private static final int DISCOUNT_PER_DESSERT = 2023;

    @Override
    public int calculateDiscount(Order order, LocalDate currentDate) {
        if (!isApplicable(currentDate)) {
            return 0;
        }
        int desertQuantity = order.getQuantityByCategory(Category.DESSERT);
        return desertQuantity * DISCOUNT_PER_DESSERT;
    }

    @Override
    public boolean isApplicable(LocalDate currentDate) {
        return super.isApplicable(currentDate) && isWeekday(currentDate);
    }

    private boolean isWeekday(LocalDate currentDate) {
        return currentDate.getDayOfWeek().getValue() < 6;
    }
}
