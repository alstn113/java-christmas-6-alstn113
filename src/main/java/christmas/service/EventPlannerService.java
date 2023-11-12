package christmas.service;

import christmas.domain.Order;
import christmas.domain.OrderResult;
import christmas.domain.VisitDate;
import christmas.domain.event.Event;
import java.util.List;

public class EventPlannerService {
    public OrderResult getOrderResult(VisitDate visitDate, Order order) {
        List<Event> events = List.of(
                Event.CHRISTMAS_DDAY_DISCOUNT,
                Event.WEEKDAY_DISCOUNT,
                Event.WEEKEND_DISCOUNT,
                Event.SPECIAL_DISCOUNT,
                Event.GIFT_EVENT
        );
        return new OrderResult(visitDate, order, events);
    }
}
