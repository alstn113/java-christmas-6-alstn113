package christmas.view;

import christmas.domain.Badge;
import christmas.domain.OrderResult;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.domain.event.Event;
import java.util.List;
import java.util.Map;

public interface OutputView {
    void displayWelcomeMessage();

    void displayEventBenefitsPreview(OrderResult orderResult);
}
