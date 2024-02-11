package christmas.constant;

import java.time.LocalDate;

public class EventConstant {
    private EventConstant() {
    }

    public static final int EVENT_YEAR = 2023;
    public static final int EVENT_MONTH = 12;
    public static final LocalDate CHRISTMAS = LocalDate.of(EVENT_YEAR, EVENT_MONTH, 25);
}
