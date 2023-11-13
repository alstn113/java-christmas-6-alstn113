package christmas.controller;

import christmas.domain.EventBenefits;
import christmas.domain.VisitDate;
import christmas.domain.event.Event;
import christmas.domain.event.EventGroup;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.util.InputUtil;
import java.util.List;

public class EventPlannerController {
    private final InputView inputView;
    private final OutputView outputView;
    private final EventGroup eventGroup;

    public EventPlannerController(final InputView inputView, final OutputView outputView,
                                  final EventGroup eventGroup) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.eventGroup = eventGroup;
    }

    public void run() {
        displayWelcomeMessage();
        VisitDate visitDate = readVisitDate();
        Order order = readOrder();
        EventBenefits eventBenefits = new EventBenefits(visitDate, order, eventGroup);
        displayEventBenefitsPreview(order, eventBenefits);

    }

    private VisitDate readVisitDate() {
        return InputUtil.retryOnException(() -> {
            int visitDateInput = inputView.readVisitDate();
            return new VisitDate(visitDateInput);
        }, true);
    }

    private Order readOrder() {
        return InputUtil.retryOnException(() -> {
            String orderInput = inputView.readOrder();
            List<OrderItem> orderItems = InputUtil.parseInputToOrderItems(orderInput);
            return new Order(orderItems);
        });
    }

    private void displayWelcomeMessage() {
        outputView.displayWelcomeMessage();
    }

    private void displayEventBenefitsPreview(Order order, EventBenefits eventBenefits) {
        outputView.displayEventBenefitsPreview(order, eventBenefits);
    }
}
