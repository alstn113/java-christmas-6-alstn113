package christmas.controller;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.domain.VisitDate;
import christmas.exception.ErrorMesssage;
import christmas.exception.InvalidInputException;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EventPlannerController {
    private final InputView inputView;
    private final OutputView outputView;

    public EventPlannerController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.displayWelcomeMessage();
        // TODO 방문 날짜 입력
        // TODO 주문 입력
        outputView.displayEventPreviewMessage();
        outputView.displayOrderedMenu();
        outputView.displayTotalPriceBeforeDiscount();
        outputView.displayGiftMenu();
        outputView.displayBenefitDetails();
        outputView.displayTotalBenefitAmount();
        outputView.displayTotalPriceAfterDiscount();
        outputView.displayDecemberEventBadge();
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
            List<OrderItem> orders = new ArrayList<>();
            String[] menuAndCount = input.split(",", -1);
            for (String menuAndCountString : menuAndCount) {
                String[] menuAndCountArray = menuAndCountString.split("-", -1);
                String menu = menuAndCountArray[0];
                int count = Integer.parseInt(menuAndCountArray[1]);
                OrderItem orderItem = new OrderItem(menu, count);
                orders.add(orderItem);
            }
            return new Order(orders);

        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(ErrorMesssage.INVALID_ORDER);
        }
    }
}
