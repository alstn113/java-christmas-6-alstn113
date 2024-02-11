package christmas.config;

import christmas.controller.EventPlannerController;
import christmas.domain.event.EventType;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.console.ConsoleInputView;
import christmas.view.console.ConsoleOutputView;
import java.util.List;

public class AppConfig {
    private AppConfig() {
    }

    public static InputView consoleInputView() {
        return new ConsoleInputView();
    }

    public static OutputView consoleOutputView() {
        return new ConsoleOutputView();
    }

    public static List<EventType> eventTypes() {
        return List.of(
                EventType.CHRISTMAS_DDAY_DISCOUNT,
                EventType.WEEKDAY_DISCOUNT,
                EventType.WEEKEND_DISCOUNT,
                EventType.SPECIAL_DISCOUNT,
                EventType.GIFT_EVENT
        );
    }

    public static EventPlannerController eventPlannerController() {
        return new EventPlannerController(
                consoleInputView(),
                consoleOutputView(),
                eventTypes()
        );
    }
}
