package christmas.dto.response;

import christmas.domain.Badge;
import christmas.domain.OrderResult;
import java.time.LocalDate;
import java.util.List;

public record EventBenefitsPreviewResponse(
        LocalDate expectedVisitDate,
        List<OrderItemResponse> orderItems,
        int totalPriceBeforeDiscount,
        List<OrderItemResponse> gifts,
        int totalBenefitPrice,
        BenefitsResponse benefits,
        int totalPriceAfterDiscount,
        String badge
) {
    public static EventBenefitsPreviewResponse from(OrderResult orderResult) {
        LocalDate expectedVisitDate = orderResult.getExpectedVisitDate().getDate();
        List<OrderItemResponse> orderItemResponses = orderResult.getOrderItems()
                .stream().map(OrderItemResponse::from).toList();
        List<OrderItemResponse> giftResponses = orderResult.getGifts()
                .stream().map(OrderItemResponse::from).toList();

        String badge = getBadgeResponse(orderResult);

        return new EventBenefitsPreviewResponse(
                expectedVisitDate,
                orderItemResponses,
                orderResult.getTotalPriceBeforeDiscount(),
                giftResponses,
                orderResult.getTotalBenefitPrice(),
                BenefitsResponse.from(orderResult.getBenefits()),
                orderResult.getTotalPriceAfterDiscount(),
                badge
        );
    }

    private static String getBadgeResponse(OrderResult orderResult) {
        String badge = null;
        if (orderResult.getBadge() != Badge.NONE) {
            badge = orderResult.getBadge().getName();
        }
        return badge;
    }
}
