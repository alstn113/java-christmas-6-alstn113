package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

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
        outputView.displayOrderedMenu();
        outputView.displayTotalPriceBeforeDiscount();
        outputView.displayGiftMenu();
        outputView.displayBenefitDetails();
        outputView.displayTotalBenefitAmount();
        outputView.displayTotalPriceAfterDiscount();
        outputView.displayDecemberEventBadge();
    }
}
