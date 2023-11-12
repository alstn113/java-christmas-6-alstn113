package christmas.domain.discount;

import christmas.domain.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDiscountStrategy extends DecemberDiscountStrategy {
    private static final LocalDate CHRISTMAS = LocalDate.of(2023, 12, 25);
    private static final int SPECIAL_DISCOUNT = 1000;

    @Override
    public int calculateDiscount(Order order) {
        return SPECIAL_DISCOUNT;
    }

    @Override
    public boolean isApplicable(LocalDate currentDate) {
        return super.isApplicable(currentDate) && isSpecialDay(currentDate);
    }

    private boolean isSpecialDay(LocalDate currentDate) {
        return currentDate.getDayOfWeek() == DayOfWeek.SUNDAY || currentDate.isEqual(CHRISTMAS);
    }
}
