package christmas.domain;

import christmas.domain.event.Event;
import christmas.domain.event.EventBenefit;
import christmas.domain.event.EventType;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderResult {
    private final List<OrderItem> gifts = new ArrayList<>();
    private final Map<EventType, Integer> benefits = new LinkedHashMap<>();
    private final int paymentAfterDiscount;

    public OrderResult(Order order, ExpectedVisitDate expectedVisitDate, List<EventType> eventTypes) {
        paymentAfterDiscount = applyEvents(order, expectedVisitDate, eventTypes);
    }

    public int applyEvents(Order order, ExpectedVisitDate expectedVisitDate, List<EventType> eventTypes) {
        LocalDate visitDate = expectedVisitDate.getDate();

        for (EventType eventType : eventTypes) {
            Event event = eventType.getEvent();
            if (!event.isApplicable(visitDate, order)) {
                continue;
            }
            EventBenefit eventBenefit = event.applyEventIfApplicable(visitDate, order);
            int benefitPrice = eventBenefit.getDiscountPrice();
            Optional<OrderItem> gift = eventBenefit.getGift();
            if (gift.isPresent()) {
                gifts.add(gift.get());
                benefitPrice += gift.get().getTotalPrice();
            }
            benefits.put(eventType, -1 * benefitPrice);
        }

        return order.getTotalPrice() + getTotalBenefits() + getGiftsPrice();
    }

    private int getGiftsPrice() {
        return gifts.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }

    public int getTotalBenefits() {
        return benefits.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Badge getBadge() {
        int totalBenefits = getTotalBenefits();
        return Badge.of(Math.abs(totalBenefits));
    }

    public List<OrderItem> getGifts() {
        return gifts;
    }

    public Map<EventType, Integer> getBenefits() {
        return benefits;
    }

    public int getPaymentAfterDiscount() {
        return paymentAfterDiscount;
    }
}
