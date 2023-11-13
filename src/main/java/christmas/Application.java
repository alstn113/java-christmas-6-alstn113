package christmas;

import christmas.controller.EventPlannerController;
import christmas.view.console.ConsoleInputView;
import christmas.view.console.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        new EventPlannerController(
                new ConsoleInputView(),
                new ConsoleOutputView()
        ).run();
    }
}
