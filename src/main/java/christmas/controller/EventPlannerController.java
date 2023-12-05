package christmas.controller;

import christmas.domain.ExpectedVisitDate;
import christmas.domain.OrderResult;
import christmas.domain.event.EventType;
import christmas.domain.order.Order;
import christmas.domain.order.OrderParser;
import christmas.dto.EventBenefitsPreviewResponse;
import christmas.dto.OrderPreviewResponse;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.util.InputUtil;
import java.util.List;

public class EventPlannerController {
    private final InputView inputView;
    private final OutputView outputView;
    private final List<EventType> eventTypes;

    public EventPlannerController(InputView inputView, OutputView outputView,
                                  List<EventType> eventTypes) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.eventTypes = eventTypes;
    }

    public void run() {
        outputView.printWelcomeMessage();

        ExpectedVisitDate expectedVisitDate = readExpectedVisitDate();
        Order order = readOrder();
        OrderResult orderResult = new OrderResult(order, expectedVisitDate, eventTypes);

        outputView.printEventBenefitPreviewMessage();
        outputView.printOrderPreview(OrderPreviewResponse.from(order));
        outputView.printEventBenefitPreview(EventBenefitsPreviewResponse.from(orderResult));
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
}
