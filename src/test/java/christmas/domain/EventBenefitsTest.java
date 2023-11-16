package christmas.domain;


import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.event.Event;
import christmas.domain.event.EventGroup;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.view.util.InputUtil;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


class EventBenefitsTest {
    private static final EventGroup DEFAULT_EVENT_GROUP = new EventGroup(
            List.of(
                    Event.CHRISTMAS_DDAY_DISCOUNT,
                    Event.WEEKDAY_DISCOUNT,
                    Event.WEEKEND_DISCOUNT,
                    Event.SPECIAL_DISCOUNT,
                    Event.GIFT_EVENT
            ));

    @Nested
    class 이벤트_혜택이_옳바른지_확인한다 {
        @Test
        void 적용된_이벤트가_하나도_없는_경우() {
            VisitDate visitDate = new VisitDate(26);
            List<OrderItem> orderItems = InputUtil.parseInputToOrderItems("타파스-1,제로콜라-1");
            Order order = new Order(orderItems);
            EventBenefits eventBenefits = new EventBenefits(visitDate, order, DEFAULT_EVENT_GROUP);

            assertThat(eventBenefits.getGiftMenus()).isEmpty();
            assertThat(eventBenefits.getBenefitsDetails()).isEmpty();
            assertThat(eventBenefits.getTotalBenefitAmount()).isZero();
            assertThat(eventBenefits.getTotalDiscountAmount()).isZero();
            assertThat(eventBenefits.getBadge().getViewName()).isEqualTo("없음");
        }

        @Test
        void 기대하는_12월_이벤트_플래너의_예시_모습() {
            VisitDate visitDate = new VisitDate(3);
            List<OrderItem> orderItems = InputUtil.parseInputToOrderItems("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            Order order = new Order(orderItems);
            EventBenefits eventBenefits = new EventBenefits(visitDate, order, DEFAULT_EVENT_GROUP);

            assertThat(eventBenefits.getGiftMenus()).isEqualTo(List.of(new OrderItem("샴페인", 1)));
            assertThat(eventBenefits.getBenefitsDetails()).isEqualTo(Map.of(
                    Event.CHRISTMAS_DDAY_DISCOUNT, -1200,
                    Event.WEEKDAY_DISCOUNT, -4046,
                    Event.SPECIAL_DISCOUNT, -1000,
                    Event.GIFT_EVENT, -25000
            ));
            assertThat(eventBenefits.getTotalBenefitAmount()).isEqualTo(-31246);
            assertThat(eventBenefits.getTotalDiscountAmount()).isEqualTo(6246);
            assertThat(eventBenefits.getBadge().getViewName()).isEqualTo("산타");
        }

        @Test
        void 평일_이벤트가_있지만_해당_안돼서_혜택_내역에_없는_예시() {
            VisitDate visitDate = new VisitDate(10);
            List<OrderItem> orderItems = InputUtil.parseInputToOrderItems("티본스테이크-1,시저샐러드-2,제로콜라-3");
            Order order = new Order(orderItems);
            EventBenefits eventBenefits = new EventBenefits(visitDate, order, DEFAULT_EVENT_GROUP);

            assertThat(eventBenefits.getGiftMenus()).isEmpty();
            assertThat(eventBenefits.getBenefitsDetails()).isEqualTo(Map.of(
                    Event.CHRISTMAS_DDAY_DISCOUNT, -1900,
                    Event.SPECIAL_DISCOUNT, -1000
            ));
            assertThat(eventBenefits.getTotalBenefitAmount()).isEqualTo(-2900);
            assertThat(eventBenefits.getTotalDiscountAmount()).isEqualTo(2900);
            assertThat(eventBenefits.getBadge().getViewName()).isEqualTo("없음");
        }
    }

}