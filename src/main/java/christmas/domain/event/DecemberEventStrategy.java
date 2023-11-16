package christmas.domain.event;

import java.time.LocalDate;

public abstract class DecemberEventStrategy extends EventStrategy {
    protected static final LocalDate EVENT_START_DATE = LocalDate.of(2023, 12, 1);
    protected static final LocalDate EVENT_END_DATE = LocalDate.of(2023, 12, 31);

    protected DecemberEventStrategy() {
        super(EVENT_START_DATE, EVENT_END_DATE);
    }
}
