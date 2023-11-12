package christmas.controller;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.domain.OrderResult;
import christmas.domain.VisitDate;
import christmas.exception.ErrorMesssage;
import christmas.exception.InvalidInputException;
import christmas.service.EventPlannerService;
import christmas.view.InputView;
import christmas.view.OutputView;
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
        outputView.displayEventPreviewMessage();

        OrderResult orderResult = eventPlannerService.getOrderResult(visitDate, order);

        outputView.displayOrderDetails(order);
        System.out.println(orderResult.getOrder().getOrderItems());

        outputView.displayTotalPriceBeforeDiscount(order.totalPrice());
        System.out.println(orderResult.getTotalPriceBeforeDiscount());

        outputView.displayGiftMenu();
        System.out.println(orderResult.getGiftMenuSummary());

        outputView.displayBenefitsDetails();
        System.out.println(orderResult.getBenefitsDetails().toString());

        outputView.displayTotalBenefitAmount();
        System.out.println(orderResult.getTotalBenefits());

        outputView.displayTotalPriceAfterDiscount();
        System.out.println(orderResult.getTotalPriceAfterDiscount());

        outputView.displayDecemberEventBadge();
        System.out.println(orderResult.getBadge());
    }

    private VisitDate readVisitDate() {
        try {
            String input = inputView.readVisitDate();
            int visitDate = Integer.parseInt(input);
            return new VisitDate(visitDate);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(ErrorMesssage.INVALID_VISIT_DATE);
        }
    }

    private Order readOrder() {
        try {
            String input = inputView.readOrder();
            String[] menuAndCount = input.split(",", -1);
            List<OrderItem> orderItems = Arrays.stream(menuAndCount)
                    .map(String::trim)
                    .map(s -> s.split("-", -1))
                    .map(s -> {
                        if (s.length != 2) {
                            throw new IllegalArgumentException();
                        }
                        return new OrderItem(s[0], Integer.parseInt(s[1]));
                    }).toList();
            return new Order(orderItems);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(ErrorMesssage.INVALID_ORDER);
        }
    }
}
