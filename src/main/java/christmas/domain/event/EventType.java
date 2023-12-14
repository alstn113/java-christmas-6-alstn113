package christmas.domain.event;

public enum EventType {
    CHRISTMAS_DDAY_DISCOUNT("크리스마스 디데이 할인", new ChristmasDdayDiscount()),
    WEEKDAY_DISCOUNT("평일 할인", new WeekdayDiscount()),
    WEEKEND_DISCOUNT("주말 할인", new WeekendDiscount()),
    SPECIAL_DISCOUNT("특별 할인", new SpecialDiscount()),
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
