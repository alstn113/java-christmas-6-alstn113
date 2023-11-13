package christmas.domain;

import christmas.domain.event.Event;
import christmas.domain.event.EventResult;
import christmas.domain.event.EventStrategy;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EventBenefits {
    private final Map<Event, Integer> benefitsDetails = new LinkedHashMap<>();
    private final List<OrderItem> giftMenus = new ArrayList<>();
    private int totalDiscountAmount = 0; // ex) 2000원

    public EventBenefits(VisitDate visitDate, Order order, List<Event> events) {
        applyEvents(visitDate, order, events);
    }

    public void applyEvents(VisitDate visitDate, Order order, List<Event> events) {
        LocalDate date = visitDate.getDate();
        events.stream()
                .filter(event -> {
                    EventStrategy eventStrategy = event.getEventStrategy();
                    return eventStrategy.isApplicable(date, order);
                })
                .forEach(event -> applyEvent(date, order, event));
    }

    private void applyEvent(LocalDate date, Order order, Event event) {
        EventStrategy eventStrategy = event.getEventStrategy();
        EventResult eventResult = eventStrategy.applyEventIfApplicable(date, order);
        int discountAmount = eventResult.getDiscountAmount();
        Optional<OrderItem> gift = eventResult.getGift();

        totalDiscountAmount += discountAmount;
        if (gift.isPresent()) {
            OrderItem giftMenu = gift.get();
            giftMenus.add(giftMenu);
            int totalGiftPrice = giftMenu.getTotalPrice();
            benefitsDetails.put(event, -1 * (discountAmount + totalGiftPrice));
            return;
        }
        benefitsDetails.put(event, -1 * discountAmount);
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

    public int getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public Badge getBadge() {
        int totalBenefits = Math.abs(getTotalBenefitAmount());
        return Badge.getBadgeByCondition(totalBenefits);
    }
}
