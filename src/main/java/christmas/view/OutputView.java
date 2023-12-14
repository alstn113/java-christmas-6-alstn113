package christmas.view;

import christmas.dto.response.BenefitsResponse;
import christmas.dto.response.GiftMenuResponse;
import christmas.dto.response.OrderResponse;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    public void printWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventBenefitsPreviewMessage() {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printOrder(OrderResponse orderResponse) {
        Map<String, Integer> order = orderResponse.orderResponse();

        System.out.println();
        System.out.println("<주문 메뉴>");
        for (Entry<String, Integer> entry : order.entrySet()) {
            String menu = entry.getKey();
            int quantity = entry.getValue();
            System.out.printf("%s: %d개%n", menu, quantity);
        }
    }

    public void printTotalPriceBeforeDiscount(int totalPrice) {
        System.out.println();
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%,d원%n", totalPrice);
    }

    public void printGiftMenu(GiftMenuResponse giftMenuResponse) {
        Map<String, Integer> giftMenus = giftMenuResponse.giftMenus();
        System.out.println();
        System.out.println("<증정 메뉴>");
        if (giftMenus.isEmpty()) {
            System.out.println("없음");
            return;
        }
        for (Entry<String, Integer> entry : giftMenus.entrySet()) {
            String menuName = entry.getKey();
            int quantity = entry.getValue();
            System.out.printf("%s: %d개%n", menuName, quantity);
        }
    }

    public void printBenefits(BenefitsResponse benefitsResponse) {
        Map<String, Integer> benefits = benefitsResponse.benefits();

        System.out.println();
        System.out.println("<혜택 내역>");
        if (benefits.isEmpty()) {
            System.out.println("없음");
            return;
        }
        for (Entry<String, Integer> entry : benefits.entrySet()) {
            String eventName = entry.getKey();
            int discount = entry.getValue();
            System.out.printf("%s: %,d원%n", eventName, discount);
        }
    }

    public void printTotalBenefitPrice(int totalBenefitPrice) {
        System.out.println();
        System.out.println("<총혜택 금액>");
        System.out.printf("%,d원%n", totalBenefitPrice);
    }

    public void printTotalPriceAfterDiscount(int totalPriceAfterDiscount) {
        System.out.println();
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf("%,d원%n", totalPriceAfterDiscount);
    }

    public void printBadge(String badge) {
        System.out.println();
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge);
    }
}