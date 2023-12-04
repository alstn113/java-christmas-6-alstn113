package christmas.domain.event;

import java.time.LocalDate;

public class ChristmasDdayDiscountEvent extends Event {
    private static final LocalDate START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate END_DATE = LocalDate.of(2023, 12, 25);
    private static final int DEFAULT_DISCOUNT_PRICE = 1_000;
    private static final int DISCOUNT_PER_DAY = 100;

    public ChristmasDdayDiscountEvent() {
        super(START_DATE, END_DATE);
    }


}
