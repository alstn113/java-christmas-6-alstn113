package christmas.controller;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.domain.OrderResult;
import christmas.domain.VisitDate;
import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import christmas.service.EventPlannerService;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.util.InputUtil;
import java.util.Arrays;
import java.util.List;

public class EventPlannerController {
    private final InputView inputView;
    private final OutputView outputView;
    private final EventPlannerService eventPlannerService;

    public EventPlannerController(final InputView inputView, final OutputView outputView,
                                  final EventPlannerService eventPlannerService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.eventPlannerService = eventPlannerService;
    }

    public void run() {
        outputView.displayWelcomeMessage();
        VisitDate visitDate = readVisitDate();
        Order order = readOrder();
        OrderResult orderResult = eventPlannerService.getOrderResult(visitDate, order);
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
