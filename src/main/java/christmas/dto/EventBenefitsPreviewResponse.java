package christmas.dto;

// 증정 메뉴, 혜택 내역, 총혜택 금액, 할인 후 예상 결제 금액, 12월 이벤트 뱃지

import christmas.domain.OrderResult;
import java.util.LinkedHashMap;
import java.util.Map;

public record EventBenefitsPreviewResponse(Map<String, Integer> gifts, Map<String, Integer> benefits,
                                           int totalBenefits, int paymentAfterDiscount, String badge) {
    public static EventBenefitsPreviewResponse from(OrderResult orderResult) {
        return new EventBenefitsPreviewResponse(
                parseGifts(orderResult),
                parseBenefits(orderResult),
                orderResult.getTotalBenefits(),
                orderResult.getPaymentAfterDiscount(),
                orderResult.getBadge().getViewName()
        );
    }

    private static Map<String, Integer> parseGifts(OrderResult orderResult) {
        Map<String, Integer> gifts = new LinkedHashMap<>();
        orderResult.getGifts().forEach(orderItem -> {
            String menuName = orderItem.menu().getViewName();
            int quantity = orderItem.quantity();
            gifts.put(menuName, quantity);
        });
        return gifts;
    }

    private static Map<String, Integer> parseBenefits(OrderResult orderResult) {
        Map<String, Integer> benefits = new LinkedHashMap<>();
        orderResult.getBenefits().forEach((eventType, benefit) -> {
            String eventName = eventType.getName();
            benefits.put(eventName, benefit);
        });
        return benefits;
    }
}
