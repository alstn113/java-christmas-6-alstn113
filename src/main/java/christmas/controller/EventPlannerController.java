package christmas.controller;

import christmas.domain.ExpectedVisitDate;
import christmas.domain.order.Order;
import christmas.domain.order.OrderParser;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.util.InputUtil;

public class EventPlannerController {
    private final InputView inputView;
    private final OutputView outputView;

    public EventPlannerController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        printWelcomeMessage();
        ExpectedVisitDate expectedVisitDate = readExpectedVisitDate();
        Order order = readOrder();
        printEventBenefitPreview();
    }

    private ExpectedVisitDate readExpectedVisitDate() {
        return InputUtil.retryOnException(() -> {
            int input = inputView.readExpectedVisitDate();
            return new ExpectedVisitDate(input);
        });
    }

    private Order readOrder() {
        return InputUtil.retryOnException(() -> {
            String input = inputView.readOrder();
            return OrderParser.parseInputToOrder(input);
        });
    }

    private void printWelcomeMessage() {
        outputView.printWelcomeMessage();
    }

    private void printEventBenefitPreview() {
        outputView.printEventBenefitPreview();
    }
}
