package christmas.domain.event.discount;

import christmas.domain.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDiscountStrategy extends DecemberDiscountStrategy {
    private static final LocalDate CHRISTMAS = LocalDate.of(2023, 12, 25);
    private static final int SPECIAL_DISCOUNT = 1000;

    @Override
    public int calculateDiscount(Order order, LocalDate currentDate) {
        if (!isSpecialDay(currentDate)) {
            return 0;
        }
        return SPECIAL_DISCOUNT;
    }

    private boolean isSpecialDay(LocalDate currentDate) {
        return currentDate.getDayOfWeek() == DayOfWeek.SUNDAY || currentDate.isEqual(CHRISTMAS);
    }
}
