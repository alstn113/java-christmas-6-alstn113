package christmas.domain.event;

import christmas.domain.event.christmas.ChristmasDdayDiscountEvent;
import christmas.domain.event.december.GiftEvent;
import christmas.domain.event.december.SpecialDiscountEvent;
import christmas.domain.event.december.WeekdayDiscountEvent;
import christmas.domain.event.december.WeekendDiscountEvent;

public enum EventType {
    CHRISTMAS_DDAY_DISCOUNT("크리스마스 디데이 할인", new ChristmasDdayDiscountEvent()),
    WEEKDAY_DISCOUNT("평일 할인", new WeekdayDiscountEvent()),
    WEEKEND_DISCOUNT("주말 할인", new WeekendDiscountEvent()),
    SPECIAL_DISCOUNT("특별 할인", new SpecialDiscountEvent()),
    GIFT_EVENT("증정 이벤트", new GiftEvent());


    private final String name;
    private final Event event;

    EventType(String name, Event event) {
        this.name = name;
        this.event = event;
    }

    public String getName() {
        return name;
    }

    public Event getEvent() {
        return event;
    }
}