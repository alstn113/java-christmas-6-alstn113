package christmas.domain.discount;

import java.time.LocalDate;

public abstract class DecemberDiscountStrategy extends DiscountStrategy {
    private static final LocalDate DECEMBER_START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate DECEMBER_END_DATE = LocalDate.of(2023, 12, 31);

    protected DecemberDiscountStrategy() {
        super(DECEMBER_START_DATE, DECEMBER_END_DATE);
    }
}
