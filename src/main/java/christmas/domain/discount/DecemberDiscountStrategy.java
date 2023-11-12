package christmas.domain.discount;

import java.time.LocalDate;

public abstract class DecemberDiscountStrategy extends DiscountStrategy {
    private static final LocalDate START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate END_DATE = LocalDate.of(2023, 12, 31);

    protected DecemberDiscountStrategy() {
        super(START_DATE, END_DATE);
    }
}
