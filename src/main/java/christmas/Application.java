package christmas;

import christmas.controller.EventPlannerController;
import christmas.domain.event.EventType;
import christmas.view.console.ConsoleInputView;
import christmas.view.console.ConsoleOutputView;
import java.util.List;

public class Application {
    private static final List<EventType> EVENT_TYPES = List.of(
            EventType.CHRISTMAS_DDAY_DISCOUNT,
            EventType.WEEKDAY_DISCOUNT,
            EventType.WEEKEND_DISCOUNT,
            EventType.SPECIAL_DISCOUNT,
            EventType.GIFT_EVENT
    );

    public static void main(String[] args) {
        new EventPlannerController(
                new ConsoleInputView(),
                new ConsoleOutputView(),
                EVENT_TYPES
        ).run();
    }
}
