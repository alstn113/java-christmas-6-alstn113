package christmas;

import christmas.controller.EventPlannerController;
import christmas.service.EventPlannerService;
import christmas.view.console.ConsoleInputView;
import christmas.view.console.ConsoleOutputView;

public class Application {
    public static void main(String[] args) {
        try {
            new EventPlannerController(
                    new ConsoleInputView(),
                    new ConsoleOutputView(),
                    new EventPlannerService()
            ).run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
