package christmas.view.console;

import christmas.dto.response.EventBenefitsPreviewResponse;
import christmas.view.OutputView;
import java.time.LocalDate;

public class ConsoleOutputView implements OutputView {
    private static final String PRICE_FORMAT = "%,d원";

    @Override
    public void printWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    @Override
    public void printEventBenefitsPreview(EventBenefitsPreviewResponse eventBenefitsPreviewResponse) {
        printEventBenefitsPreviewMessage(eventBenefitsPreviewResponse);
        System.out.println();
        printOrderMenus(eventBenefitsPreviewResponse);
        System.out.println();
        printTotalPriceBeforeDiscount(eventBenefitsPreviewResponse);
        System.out.println();
        printGiftMenus(eventBenefitsPreviewResponse);
        System.out.println();
        printBenefits(eventBenefitsPreviewResponse);
        System.out.println();
        printTotalBenefitPrice(eventBenefitsPreviewResponse);
        System.out.println();
        printTotalPriceAfterDiscount(eventBenefitsPreviewResponse);
        System.out.println();
        printBadge(eventBenefitsPreviewResponse);
    }

    private static void printEventBenefitsPreviewMessage(EventBenefitsPreviewResponse eventBenefitsPreviewResponse) {
        LocalDate expectedVisitDate = eventBenefitsPreviewResponse.expectedVisitDate();
        int month = expectedVisitDate.getMonthValue();
        int day = expectedVisitDate.getDayOfMonth();
        System.out.printf("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n", month, day);
    }

    private static void printOrderMenus(EventBenefitsPreviewResponse eventBenefitsPreviewResponse) {
        System.out.println("<주문 메뉴>");
        eventBenefitsPreviewResponse.orderItems().forEach(orderItemResponse -> {
            String menuName = orderItemResponse.menuName();
            int quantity = orderItemResponse.quantity();
            System.out.printf("%s %d개%n", menuName, quantity);
        });
    }

    private static void printTotalPriceBeforeDiscount(EventBenefitsPreviewResponse eventBenefitsPreviewResponse) {
        System.out.println("<할인 전 총주문 금액>");
        int totalPriceBeforeDiscount = eventBenefitsPreviewResponse.totalPriceBeforeDiscount();
        System.out.printf(PRICE_FORMAT + "%n", totalPriceBeforeDiscount);
    }

    private static void printGiftMenus(EventBenefitsPreviewResponse eventBenefitsPreviewResponse) {
        System.out.println("<증정 메뉴>");
        if (eventBenefitsPreviewResponse.gifts().isEmpty()) {
            System.out.println("없음");
            return;
        }
        eventBenefitsPreviewResponse.gifts().forEach(gift -> {
            String menuName = gift.menuName();
            int quantity = gift.quantity();
            System.out.printf("%s %d개%n", menuName, quantity);
        });
    }

    private static void printBenefits(EventBenefitsPreviewResponse eventBenefitsPreviewResponse) {
        System.out.println("<혜택 내역>");
        if (eventBenefitsPreviewResponse.benefits().benefitsResponse().isEmpty()) {
            System.out.println("없음");
            return;
        }
        eventBenefitsPreviewResponse.benefits().benefitsResponse().forEach((eventType, discount) -> {
            System.out.printf("%s: %s%n", eventType, String.format(PRICE_FORMAT, discount * -1));
        });
    }

    private static void printTotalBenefitPrice(EventBenefitsPreviewResponse eventBenefitsPreviewResponse) {
        System.out.println("<총혜택 금액>");
        int totalBenefitPrice = eventBenefitsPreviewResponse.totalBenefitPrice();
        System.out.printf(PRICE_FORMAT + "%n", totalBenefitPrice * -1);
    }

    private static void printTotalPriceAfterDiscount(EventBenefitsPreviewResponse eventBenefitsPreviewResponse) {
        System.out.println("<할인 후 예상 결제 금액>");
        int totalPriceAfterDiscount = eventBenefitsPreviewResponse.totalPriceAfterDiscount();
        System.out.printf(PRICE_FORMAT + "%n", totalPriceAfterDiscount);
    }

    private static void printBadge(EventBenefitsPreviewResponse eventBenefitsPreviewResponse) {
        System.out.println("<12월 이벤트 배지>");
        if (eventBenefitsPreviewResponse.badge() == null) {
            System.out.println("없음");
            return;
        }
        System.out.println(eventBenefitsPreviewResponse.badge());
    }
}
