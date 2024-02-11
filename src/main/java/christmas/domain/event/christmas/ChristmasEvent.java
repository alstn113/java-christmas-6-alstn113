package christmas.domain.event.christmas;

import static christmas.constant.EventConstant.EVENT_MONTH;
import static christmas.constant.EventConstant.EVENT_YEAR;

import christmas.domain.event.Event;
import java.time.LocalDate;

public abstract class ChristmasEvent extends Event {
    protected static final LocalDate EVENT_START_DATE = LocalDate.of(EVENT_YEAR, EVENT_MONTH, 1);
    protected static final LocalDate EVENT_END_DATE = LocalDate.of(EVENT_YEAR, EVENT_MONTH, 25);

    protected ChristmasEvent() {
        super(EVENT_START_DATE, EVENT_END_DATE);
    }
}
