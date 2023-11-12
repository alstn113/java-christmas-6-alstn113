package christmas.domain.discount;

import christmas.domain.Order;
import java.time.LocalDate;

public abstract class DiscountStrategy {
    private final LocalDate startDate;
    private final LocalDate endDate;

    protected DiscountStrategy(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public abstract int calculateDiscount(Order order);

    public boolean isApplicable(LocalDate currentDate) {
        // ex) 1~5일이면 1일, 5일은 포함
        return !currentDate.isBefore(startDate) && !currentDate.isAfter(endDate);
    }
}
