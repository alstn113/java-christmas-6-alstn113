package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.order.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EventTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 15, 25})
    void 이벤트에_해당하는_경우(int day) {
        Event event = new TestEvent();
        LocalDate date = LocalDate.of(2023, 12, day);
        OrderItem orderItem = new OrderItem(Menu.CHOCO_CAKE, 2);
        Order order = new Order(List.of(orderItem));

        assertThat(event.isApplicable(date, order)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {26, 30, 31})
    void 이벤트_진행_기간이_아닌_경우(int day) {
        Event event = new TestEvent();
        LocalDate date = LocalDate.of(2023, 12, day);
        OrderItem orderItem = new OrderItem(Menu.CHOCO_CAKE, 2);
        Order order = new Order(List.of(orderItem));

        assertThat(event.isApplicable(date, order)).isFalse();
    }

    @Test
    void 총_주문_금액이_10000원_이상이_아닌_경우() {
        Event event = new TestEvent();
        LocalDate date = LocalDate.of(2023, 12, 15);
        OrderItem orderItem = new OrderItem(Menu.MUSHROOM_SOUP, 1);
        Order order = new Order(List.of(orderItem));

        assertThat(event.isApplicable(date, order)).isFalse();
    }

    private static class TestEvent extends Event {
        public TestEvent() {
            super(LocalDate.of(2023, 12, 1),
                    LocalDate.of(2023, 12, 25));
        }

        @Override
        protected EventBenefit applyEvent(LocalDate date, Order order) {
            return EventBenefit.of(0);
        }
    }
}