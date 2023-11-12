package christmas.view;

import christmas.domain.Badge;
import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.domain.event.Event;
import java.util.List;
import java.util.Map;

public interface OutputView {
    void displayWelcomeMessage();

    void displayEventPreviewMessage();

    void displayOrderDetails(Order order);

    void displayTotalPriceBeforeDiscount(int totalPrice);

    void displayGiftMenus(List<OrderItem> giftMenus);

    void displayBenefitsDetails(Map<Event, Integer> benefitsDetails);

    void displayTotalBenefitAmount(int totalBenefitAmount);

    void displayTotalPriceAfterDiscount(int totalPriceAfterDiscount);

    void displayDecemberEventBadge(Badge badge);
}
