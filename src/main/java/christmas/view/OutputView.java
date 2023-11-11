package christmas.view;

import christmas.domain.Order;

public interface OutputView {
    void displayWelcomeMessage();

    void displayEventPreviewMessage();

    void displayOrderDetails(Order order);

    void displayTotalPriceBeforeDiscount(Order order);

    void displayGiftMenu();

    void displayBenefitDetails();

    void displayTotalBenefitAmount();

    void displayTotalPriceAfterDiscount();

    void displayDecemberEventBadge();
}
