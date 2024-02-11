package christmas.domain;

import christmas.domain.event.Event;
import christmas.domain.event.EventBenefit;
import christmas.domain.event.EventType;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderResult {
    private final Order order;
    private final ExpectedVisitDate expectedVisitDate;
    private final List<OrderItem> gifts = new ArrayList<>();
    private final Map<EventType, Integer> benefits = new LinkedHashMap<>();

    private OrderResult(ExpectedVisitDate expectedVisitDate, Order order, List<EventType> eventTypes) {
        this.order = order;
        this.expectedVisitDate = expectedVisitDate;
        applyEvents(expectedVisitDate, order, eventTypes);
    }

    public static OrderResult of(ExpectedVisitDate expectedVisitDate, Order order, List<EventType> eventTypes) {
        return new OrderResult(expectedVisitDate, order, eventTypes);
    }

    private void applyEvents(ExpectedVisitDate expectedVisitDate, Order order, List<EventType> eventTypes) {
        for (EventType eventType : eventTypes) {
            Event event = eventType.getEvent();
            if (event.isApplicable(expectedVisitDate.getDate(), order)) {
                EventBenefit eventBenefit = event.applyEvent(expectedVisitDate, order);
                eventBenefit.getGift().map(gifts::add);
                int discount = eventBenefit.getDiscount() + eventBenefit.getGiftPrice();
                benefits.put(eventType, discount);
            }
        }
    }

    public ExpectedVisitDate getExpectedVisitDate() {
        return expectedVisitDate;
    }


    public List<OrderItem> getOrderItems() {
        return order.getOrderItems();
    }

    public int getTotalPriceBeforeDiscount() {
        return order.getTotalPrice();
    }

    public List<OrderItem> getGifts() {
        return gifts;
    }


    public int getTotalBenefitPrice() {
        return benefits.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Map<EventType, Integer> getBenefits() {
        return benefits;
    }


    public int getTotalPriceAfterDiscount() {
        int totalBenefitPrice = getTotalBenefitPrice();
        int giftsPrice = gifts.stream()
                .mapToInt(OrderItem::getPrice)
                .sum();

        return getTotalPriceBeforeDiscount() - totalBenefitPrice + giftsPrice;
    }

    public Badge getBadge() {
        return Badge.of(getTotalBenefitPrice());
    }

}
