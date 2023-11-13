package christmas.controller;

import christmas.domain.OrderResult;
import christmas.domain.VisitDate;
import christmas.domain.event.Event;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.util.InputUtil;
import java.util.Arrays;
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
        OrderResult orderResult = new OrderResult(visitDate, order, EVENTS);
        displayEventBenefitsPreview(orderResult);
    }

    private VisitDate readVisitDate() {
        return InputUtil.retryOnException(() -> {
            try {
                int visitDateInput = inputView.readVisitDate();
                return new VisitDate(visitDateInput);
            } catch (IllegalArgumentException e) {
                throw new InvalidInputException(ErrorMessage.INVALID_VISIT_DATE);
            }
        }, true);
    }

    private Order readOrder() {
        return InputUtil.retryOnException(() -> {
            try {
                String orderInput = inputView.readOrder();
                List<OrderItem> orderItems = parseToOrderItems(orderInput);
                return new Order(orderItems);
            } catch (IllegalArgumentException e) {
                throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
            }
        });
    }

    private List<OrderItem> parseToOrderItems(String input) {
        String[] menuAndCount = input.split(",", -1);
        return Arrays.stream(menuAndCount)
                .map(String::trim)
                .map(this::parseToMenuAndQuantity)
                .toList();
    }


    private OrderItem parseToMenuAndQuantity(String input) {
        String[] menuAndCount = input.split("-", -1);
        if (menuAndCount.length != 2) {
            throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
        }
        String menu = menuAndCount[0];
        int quantity = Integer.parseInt(menuAndCount[1]);
        return new OrderItem(menu, quantity);
    }

    private void displayWelcomeMessage() {
        outputView.displayWelcomeMessage();
    }

    private void displayEventBenefitsPreview(OrderResult orderResult) {
        outputView.displayEventBenefitsPreview(orderResult);
    }
}
