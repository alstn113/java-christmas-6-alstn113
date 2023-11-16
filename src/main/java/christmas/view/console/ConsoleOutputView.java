package christmas.view.console;

import christmas.domain.Badge;
import christmas.domain.EventBenefits;
import christmas.domain.event.Event;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.view.OutputView;
import java.util.List;
import java.util.Map;

public class ConsoleOutputView implements OutputView {
    private static final String PRICE_FORMAT = "%,d원";

    @Override
    public void displayWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    @Override
    public void displayEventBenefitsPreview(Order order, EventBenefits eventBenefits) {
        int totalPriceBeforeDiscount = order.totalPrice();
        int totalPriceAfterDiscount = totalPriceBeforeDiscount - eventBenefits.getTotalDiscountAmount();

        displayEventBenefitsPreviewMessage();
        displayOrderDetails(order.getOrderItems());
        displayTotalPriceBeforeDiscount(totalPriceBeforeDiscount);
        displayGiftMenus(eventBenefits.getGiftMenus());
        displayBenefitsDetails(eventBenefits.getBenefitsDetails());
        displayTotalBenefitAmount(eventBenefits.getTotalBenefitAmount());
        displayTotalPriceAfterDiscount(totalPriceAfterDiscount);
        displayDecemberEventBadge(eventBenefits.getBadge());
    }

    public void displayEventBenefitsPreviewMessage() {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void displayOrderDetails(List<OrderItem> orderItems) {
        System.out.println();
        System.out.println("<주문 메뉴>");
        for (OrderItem orderItem : orderItems) {
            String menuName = orderItem.getMenu().getViewName();
            int quantity = orderItem.getQuantity();
            System.out.printf("%s %d개%n", menuName, quantity);
        }
    }

    public void displayTotalPriceBeforeDiscount(int totalPrice) {
        System.out.println();
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf(PRICE_FORMAT + "%n", totalPrice);
    }

    public void displayGiftMenus(List<OrderItem> giftMenus) {
        System.out.println();
        System.out.println("<증정 메뉴>");
        if (giftMenus.isEmpty()) {
            System.out.println("없음");
            return;
        }
        for (OrderItem giftMenu : giftMenus) {
            String menuName = giftMenu.getMenu().getViewName();
            int quantity = giftMenu.getQuantity();
            System.out.printf("%s %d개%n", menuName, quantity);
        }
    }

    public void displayBenefitsDetails(Map<Event, Integer> benefitsDetails) {
        System.out.println();
        System.out.println("<혜택 내역>");
        if (benefitsDetails.isEmpty()) {
            System.out.println("없음");
            return;
        }

        for (Map.Entry<Event, Integer> entry : benefitsDetails.entrySet()) {
            String eventViewName = entry.getKey().getViewName();
            int benefitAmount = entry.getValue();
            System.out.printf("%s %s%n", eventViewName, String.format(PRICE_FORMAT, benefitAmount));
        }
    }

    public void displayTotalBenefitAmount(int totalBenefitAmount) {
        System.out.println();
        System.out.println("<총혜택 금액>");
        System.out.printf(PRICE_FORMAT + "%n", totalBenefitAmount);
    }

    public void displayTotalPriceAfterDiscount(int totalPriceAfterDiscount) {
        System.out.println();
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf(PRICE_FORMAT + "%n", totalPriceAfterDiscount);
    }

    public void displayDecemberEventBadge(Badge badge) {
        System.out.println();
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge.getViewName());
    }
}
