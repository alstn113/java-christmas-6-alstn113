package christmas.domain;

import christmas.domain.event.Event;
import christmas.domain.event.EventBenefit;
import christmas.domain.event.EventType;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderResult {
    private final Order order;
    private final Map<EventType, Integer> benefits = new LinkedHashMap<>();
    private final List<OrderItem> giftMenus = new ArrayList<>();

    public OrderResult(ExpectedVisitDate date, Order order, List<EventType> eventTypes) {
        this.order = order;
        applyEvents(date, order, eventTypes);
    }

    private void applyEvents(ExpectedVisitDate date, Order order, List<EventType> eventTypes) {
        for (EventType eventType : eventTypes) {
            Event event = eventType.getEvent();

            if (event.isApplicable(date.getDate(), order)) {
                EventBenefit eventBenefit = event.applyEvent(date, order);
                int discount = eventBenefit.getDiscount();
                if (eventBenefit.getGift().isPresent()) {
                    OrderItem gift = eventBenefit.getGift().get();
                    discount += gift.menu().getPrice() * gift.quantity();
                    giftMenus.add(gift);
                }
                benefits.put(eventType, -1 * discount);
            }
        }
    }

    public int getTotalBenefitPrice() {
        return benefits.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public List<OrderItem> getGiftMenu() {
        return giftMenus;
    }

    public int getTotalPriceAfterDiscount() {
        int totalBenefitPrice = getTotalBenefitPrice();
        int giftPrice = giftMenus.stream()
                .mapToInt(orderItem -> orderItem.menu().getPrice() * orderItem.quantity())
                .sum();
        int totalPriceBeforeDiscount = order.getTotalPrice();
        return totalPriceBeforeDiscount + totalBenefitPrice + giftPrice;
    }

    public Badge getBadge() {
        int totalBenefitPrice = Math.abs(getTotalBenefitPrice());
        return Badge.of(totalBenefitPrice);
    }

    public Map<EventType, Integer> getBenefits() {
        return benefits;
    }
}
