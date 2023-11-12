package christmas.controller;

import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.domain.OrderResult;
import christmas.domain.VisitDate;
import christmas.domain.event.Event;
import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.util.InputUtil;
import java.util.Arrays;
import java.util.List;

public class EventPlannerController {
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
        OrderResult orderResult = generateOrderResult(visitDate, order);
        displayEventBenefitsPreview(orderResult);
    }

    private VisitDate readVisitDate() {
        return InputUtil.retryOnException(() -> {
            try {
                int visitDate = inputView.readVisitDate();
                return new VisitDate(visitDate);
            } catch (IllegalArgumentException e) {
                throw new InvalidInputException(ErrorMessage.INVALID_VISIT_DATE);
            }
        }, true);
    }

    private Order readOrder() {
        return InputUtil.retryOnException(() -> {
            try {
                String input = inputView.readOrder();
                String[] menuAndCount = input.split(",", -1);
                List<OrderItem> orderItems = Arrays.stream(menuAndCount)
                        .map(String::trim)
                        .map(this::parseMenuAndQuantity)
                        .toList();
                return new Order(orderItems);
            } catch (IllegalArgumentException e) {
                throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
            }
        });
    }

    private OrderItem parseMenuAndQuantity(String input) {
        String[] menuAndCount = input.split("-", -1);
        if (menuAndCount.length != 2) {
            throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
        }
        return new OrderItem(menuAndCount[0], Integer.parseInt(menuAndCount[1]));
    }

    private OrderResult generateOrderResult(VisitDate visitDate, Order order) {
        List<Event> events = List.of(
                Event.CHRISTMAS_DDAY_DISCOUNT,
                Event.WEEKDAY_DISCOUNT,
                Event.WEEKEND_DISCOUNT,
                Event.SPECIAL_DISCOUNT,
                Event.GIFT_EVENT
        );
        return new OrderResult(visitDate, order, events);
    }

    private void displayWelcomeMessage() {
        outputView.displayWelcomeMessage();
    }

    private void displayEventBenefitsPreview(OrderResult orderResult) {
        outputView.displayEventPreviewMessage();
        outputView.displayOrderDetails(orderResult.getOrder());
        outputView.displayTotalPriceBeforeDiscount(orderResult.getTotalPriceBeforeDiscount());
        outputView.displayGiftMenus(orderResult.getGiftMenus());
        outputView.displayBenefitsDetails(orderResult.getBenefitsDetails());
        outputView.displayTotalBenefitAmount(orderResult.getTotalBenefitAmount());
        outputView.displayTotalPriceAfterDiscount(orderResult.getTotalPriceAfterDiscount());
        outputView.displayDecemberEventBadge(orderResult.getBadge());
    }
}
