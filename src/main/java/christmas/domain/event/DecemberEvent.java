package christmas.domain.event;

import java.time.LocalDate;

public abstract class DecemberEvent extends Event {
    private static final LocalDate START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate END_DATE = LocalDate.of(2023, 12, 31);

    protected DecemberEvent() {
        super(START_DATE, END_DATE);
    }
}
