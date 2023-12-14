package christmas.view;

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
}