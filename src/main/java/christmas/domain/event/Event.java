package christmas.domain.event;

public enum Event {
    CHRISTMAS_DDAY_DISCOUNT("크리스마스 디데이 할인", new ChristmasDdayDiscountStrategy()),
    WEEKDAY_DISCOUNT("평일 할인", new WeekdayDiscountStrategy()),
    WEEKEND_DISCOUNT("주말 할인", new WeekendDiscountStrategy()),
    SPECIAL_DISCOUNT("특별 할인", new SpecialDiscountStrategy()),
    GIFT_EVENT("증정 이벤트", new GiftEventStrategy());

    private final String viewName;
    private final EventStrategy eventStrategy;

    Event(String viewName, EventStrategy eventStrategy) {
        this.viewName = viewName;
        this.eventStrategy = eventStrategy;
    }

    public String getViewName() {
        return viewName;
    }

    public EventStrategy getEventStrategy() {
        return eventStrategy;
    }
}
