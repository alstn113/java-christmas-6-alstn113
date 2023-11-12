package christmas.view.console;

import christmas.domain.Badge;
import christmas.domain.OrderResult;
import christmas.domain.event.Event;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.view.OutputView;
import java.util.List;
import java.util.Map;

public class ConsoleOutputView implements OutputView {
    @Override
    public void displayWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    @Override
    public void displayEventBenefitsPreview(OrderResult orderResult) {
        displayEventPreviewMessage();
        displayOrderDetails(orderResult.getOrder());
        displayTotalPriceBeforeDiscount(orderResult.getTotalPriceBeforeDiscount());
        displayGiftMenus(orderResult.getGiftMenus());
        displayBenefitsDetails(orderResult.getBenefitsDetails());
        displayTotalBenefitAmount(orderResult.getTotalBenefitAmount());
        displayTotalPriceAfterDiscount(orderResult.getTotalPriceAfterDiscount());
        displayDecemberEventBadge(orderResult.getBadge());
    }

    private void displayEventPreviewMessage() {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    private void displayOrderDetails(Order order) {
        System.out.println();
        System.out.println("<주문 메뉴>");
        for (OrderItem orderItem : order.getOrderItems()) {
            String menuName = orderItem.getMenu().getViewName();
            int quantity = orderItem.getQuantity();
            System.out.printf("%s %d개%n", menuName, quantity);
        }
    }

    private void displayTotalPriceBeforeDiscount(int totalPrice) {
        System.out.println();
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%,d원%n", totalPrice);
    }

    private void displayGiftMenus(List<OrderItem> giftMenus) {
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

    private void displayBenefitsDetails(Map<Event, Integer> benefitsDetails) {
        System.out.println();
        System.out.println("<혜택 내역>");
        if (benefitsDetails.isEmpty()) {
            System.out.println("없음");
            return;
        }

        for (Map.Entry<Event, Integer> entry : benefitsDetails.entrySet()) {
            Event event = entry.getKey();
            int benefitAmount = entry.getValue();
            System.out.printf("%s: %,d원%n", event.getViewName(), benefitAmount);
        }
    }

    private void displayTotalBenefitAmount(int totalBenefitAmount) {
        System.out.println();
        System.out.println("<총혜택 금액>");
        System.out.printf("%,d원%n", totalBenefitAmount);
    }

    private void displayTotalPriceAfterDiscount(int totalPriceAfterDiscount) {
        System.out.println();
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%,d원%n", totalPriceAfterDiscount);
    }

    private void displayDecemberEventBadge(Badge badge) {
        System.out.println();
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge.getViewName());
    }
}
