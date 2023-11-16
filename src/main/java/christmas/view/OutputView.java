package christmas.view;

import christmas.domain.EventBenefits;
import christmas.domain.order.Order;

public interface OutputView {
    void displayWelcomeMessage();

    void displayEventBenefitsPreview(Order order, EventBenefits eventBenefits);
}
