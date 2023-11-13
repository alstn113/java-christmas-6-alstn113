package christmas.domain;

import christmas.domain.event.Event;
import christmas.domain.event.EventResult;
import christmas.domain.event.EventStrategy;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderResult {
    private final Order order;
    private final Map<Event, Integer> benefitsDetails = new LinkedHashMap<>();
    private final List<OrderItem> giftMenus = new ArrayList<>();
    private int totalDiscountAmount = 0; // ex) 2000원

    public OrderResult(VisitDate visitDate, Order order, List<Event> events) {
        this.order = order;
        applyEvents(visitDate, order, events);
    }

    public void applyEvents(VisitDate visitDate, Order order, List<Event> events) {
        for (Event event : events) {
            EventStrategy eventStrategy = event.getEventStrategy();
            if (!eventStrategy.isApplicable(visitDate.getDate(), order)) {
                continue;
            }

            EventResult eventResult = eventStrategy.applyEventIfApplicable(visitDate.getDate(), order);
            int discountAmount = eventResult.getDiscountAmount();
            totalDiscountAmount += discountAmount;
            eventResult.getGift().ifPresent(giftMenus::add);
            int totalGiftPrice = getTotalGiftPrice();
            benefitsDetails.put(event, -1 * (discountAmount + totalGiftPrice));
        }
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
        int totalGiftPrice = getTotalGiftPrice();
        return -1 * (totalDiscountAmount + totalGiftPrice);
    }

    private int getTotalGiftPrice() {
        return giftMenus.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }

    public int getTotalPriceAfterDiscount() {
        return getTotalPriceBeforeDiscount() - totalDiscountAmount;
    }

    public Badge getBadge() {
        int totalBenefits = Math.abs(getTotalBenefitAmount());
        return Badge.getBadgeByCondition(totalBenefits);
    }
}
