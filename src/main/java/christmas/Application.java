package christmas;

import christmas.controller.EventPlannerController;
import christmas.domain.event.Event;
import christmas.domain.event.EventGroup;
import christmas.view.console.ConsoleInputView;
import christmas.view.console.ConsoleOutputView;
import java.util.List;

public class Application {
    private static final List<Event> DEFAULT_EVENTS = List.of(
            Event.CHRISTMAS_DDAY_DISCOUNT,
            Event.WEEKDAY_DISCOUNT,
            Event.WEEKEND_DISCOUNT,
            Event.SPECIAL_DISCOUNT,
            Event.GIFT_EVENT
    );

    public static void main(String[] args) {
        new EventPlannerController(
                new ConsoleInputView(),
                new ConsoleOutputView(),
                new EventGroup(DEFAULT_EVENTS)
        ).run();
    }
}
