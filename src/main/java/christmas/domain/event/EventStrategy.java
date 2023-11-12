package christmas.domain.event;

import java.time.LocalDate;

public abstract class EventStrategy {
    private final LocalDate startDate;
    private final LocalDate endDate;

    protected EventStrategy(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isInProgress(LocalDate currentDate) {
        // ex) 1~5일이면 1일, 5일은 포함
        return !currentDate.isBefore(startDate) && !currentDate.isAfter(endDate);
    }
}
