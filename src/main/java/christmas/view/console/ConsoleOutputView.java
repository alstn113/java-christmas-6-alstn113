package christmas.view.console;

import christmas.dto.EventBenefitsPreviewResponse;
import christmas.dto.OrderPreviewResponse;
import christmas.view.OutputView;
import java.util.Map;

public class ConsoleOutputView implements OutputView {
    private static final String PRICE_FORMAT = "%,d원";

    @Override
    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void printWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    @Override
    public void printEventBenefitPreviewMessage() {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    @Override
    public void printOrderPreview(OrderPreviewResponse orderPreviewResponse) {
        System.out.println();
        System.out.println("<주문 메뉴>");
        orderPreviewResponse.orderItems().forEach((menuName, quantity) -> {
            System.out.printf("%s %d개%n", menuName, quantity);
        });
        System.out.println();
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf(PRICE_FORMAT + "%n", orderPreviewResponse.totalAmount());

    }

    @Override
    public void printEventBenefitPreview(EventBenefitsPreviewResponse eventBenefitsPreviewResponse) {
        printGiftsMenu(eventBenefitsPreviewResponse.gifts());
        System.out.println();
        printBenefits(eventBenefitsPreviewResponse.benefits());
        System.out.println();
        System.out.println("<총혜택 금액>");
        System.out.printf(PRICE_FORMAT + "%n", eventBenefitsPreviewResponse.totalBenefits());
        System.out.println();
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf(PRICE_FORMAT + "%n", eventBenefitsPreviewResponse.paymentAfterDiscount());
        System.out.println();
        System.out.println("<12월 이벤트 배지>");
        System.out.println(eventBenefitsPreviewResponse.badge());
    }

    private void printGiftsMenu(Map<String, Integer> gifts) {
        System.out.println("<증정 메뉴>");
        if (gifts.isEmpty()) {
            System.out.println("없음");
            return;
        }
        gifts.forEach((menuName, quantity) -> {
            System.out.printf("%s %d개%n", menuName, quantity);
        });
    }

    private void printBenefits(Map<String, Integer> benefits) {
        System.out.println("<혜택 내역>");
        if (benefits.isEmpty()) {
            System.out.println("없음");
            return;
        }
        benefits.forEach((menuName, quantity) -> {
            System.out.printf("%s: " + PRICE_FORMAT + "%n", menuName, quantity);
        });
    }
}
