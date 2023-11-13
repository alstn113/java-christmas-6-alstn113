package christmas.controller;

import christmas.domain.EventBenefits;
import christmas.domain.VisitDate;
import christmas.domain.event.Event;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.util.InputUtil;
import java.util.List;

public class EventPlannerController {
    private static final List<Event> EVENTS = List.of(
            Event.CHRISTMAS_DDAY_DISCOUNT,
            Event.WEEKDAY_DISCOUNT,
            Event.WEEKEND_DISCOUNT,
            Event.SPECIAL_DISCOUNT,
            Event.GIFT_EVENT
    );
    private final InputView inputView;
    private final OutputView outputView;

    public EventPlannerController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        displayWelcomeMessage();
        VisitDate visitDate = readVisitDate();
        Order order = readOrder();
        EventBenefits eventBenefits = new EventBenefits(visitDate, order, EVENTS);
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
            List<OrderItem> orderItems = InputUtil.parseOrderItems(orderInput);
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
