package christmas.domain.discount;

import christmas.domain.Order;

public abstract class DiscountStrategy {
    private final int startDate;
    private final int endDate;

    protected DiscountStrategy(int startDate, int endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public abstract int calculateDiscount(Order order);

    public boolean isApplicable(int currentDate) {
        return currentDate >= startDate && currentDate <= endDate;
    }
}
