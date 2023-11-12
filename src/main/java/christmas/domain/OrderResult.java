package christmas.domain;

import christmas.domain.event.Event;
import christmas.domain.event.EventResult;
import christmas.domain.event.EventStrategy;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderResult {
    private final Order order;
    private final Map<Event, Integer> benefitsDetails;
    private final List<OrderItem> giftMenus = new ArrayList<>();
    private int totalDiscountAmount = 0; // ex) 2000원

    public OrderResult(VisitDate visitDate, Order order, List<Event> events) {
        this.order = order;
        this.benefitsDetails = applyEvents(visitDate, order, events);
    }

    public Map<Event, Integer> applyEvents(VisitDate visitDate, Order order, List<Event> events) {
        Map<Event, Integer> benefits = new LinkedHashMap<>();
        for (Event event : events) {
            EventStrategy eventStrategy = event.getEventStrategy();
            if (!eventStrategy.isApplicable(visitDate.getDate(), order)) {
                continue;
            }
            EventResult eventResult = eventStrategy.applyEvent(visitDate.getDate(), order);
            int discountAmount = eventResult.getDiscountAmount();
            totalDiscountAmount += discountAmount;
            eventResult.getGift().ifPresent(giftMenus::add);
            int totalGiftPrice = giftMenus.stream()
                    .mapToInt(giftMenu -> giftMenu.quantity() * giftMenu.price())
                    .sum();
            benefits.put(event, -1 * (discountAmount + totalGiftPrice));

        }
        return benefits;
    }

    public Order getOrder() {
        return order;
    }

    public int getTotalPriceBeforeDiscount() {
        return order.totalPrice();
    }

    public List<OrderItem> getGiftMenus() {
        return giftMenus;
    }

    public Map<Event, Integer> getBenefitsDetails() {
        return benefitsDetails;
    }

    public int getTotalBenefitAmount() {
        int totalGiftPrice = giftMenus.stream()
                .mapToInt(giftMenu -> giftMenu.quantity() * giftMenu.price())
                .sum();
        return -1 * (totalDiscountAmount + totalGiftPrice);
    }

    public int getTotalPriceAfterDiscount() {
        return getTotalPriceBeforeDiscount() - totalDiscountAmount;
    }

    public Badge getBadge() {
        int totalBenefits = Math.abs(getTotalBenefitAmount());
        return Badge.getBadgeByCondition(totalBenefits);
    }
}
