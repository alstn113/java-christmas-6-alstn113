package christmas.domain.event;

import christmas.domain.event.discount.ChristmasDdayDiscountStrategy;
import christmas.domain.event.discount.SpecialDiscountStrategy;
import christmas.domain.event.discount.WeekdayDiscountStrategy;
import christmas.domain.event.discount.WeekendDiscountStrategy;
import christmas.domain.event.gift.DecemberGiftStrategy;

public enum Event {
    CHRISTMAS_DDAY_DISCOUNT("크리스마스 디데이 할인", new ChristmasDdayDiscountStrategy()),
    WEEKDAY_DISCOUNT("평일 할인", new WeekdayDiscountStrategy()),
    WEEKEND_DISCOUNT("주말 할인", new WeekendDiscountStrategy()),
    SPECIAL_DISCOUNT("특별 할인", new SpecialDiscountStrategy()),
    GIFT_EVENT("증정 이벤트", new DecemberGiftStrategy());

    private final String viewName;
    private final EventStrategy eventStrategy;

    Event(String viewName, EventStrategy eventStrategy) {
        this.viewName = viewName;
        this.eventStrategy = eventStrategy;
    }
}
