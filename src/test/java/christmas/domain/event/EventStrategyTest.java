package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("EventStrategy 추상 클래스")
class EventStrategyTest {

    @Test
    @DisplayName("이벤트 진행 중이고, 총 주문 금액이 10,000원 이상인 경우")
    void isApplicableTest() {
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 25);

        EventStrategy eventStrategy = new EventStrategyMock(startDate, endDate);

        List<OrderItem> orderItems = List.of(
                new OrderItem("티본스테이크", 2),
                new OrderItem("초코케이크", 5),
                new OrderItem("아이스크림", 3),
                new OrderItem("제로콜라", 3)
        );
        Order order = new Order(orderItems);
        LocalDate currentDate = LocalDate.of(2023, 12, 10);

        assertThat(eventStrategy.isApplicable(currentDate, order)).isTrue();
    }

    @Test
    @DisplayName("이벤트 진행 중이고, 총 주문 금액이 10,000원 미만인 경우")
    void isApplicableTest_LessThanCondition() {
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 25);

        EventStrategy eventStrategy = new EventStrategyMock(startDate, endDate);

        List<OrderItem> orderItems = List.of(
                new OrderItem("아이스크림", 1),
                new OrderItem("제로콜라", 1)
        );
        Order order = new Order(orderItems);
        LocalDate currentDate = LocalDate.of(2023, 12, 5);

        assertThat(eventStrategy.isApplicable(currentDate, order)).isFalse();
    }

    @Test
    @DisplayName("이벤트 기간이 아닌 경우")
    void isApplicableTest_NotDuringEvent() {
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 25);

        EventStrategy eventStrategy = new EventStrategyMock(startDate, endDate);

        List<OrderItem> orderItems = List.of(
                new OrderItem("티본스테이크", 2),
                new OrderItem("초코케이크", 5),
                new OrderItem("아이스크림", 3),
                new OrderItem("제로콜라", 3)
        );
        Order order = new Order(orderItems);
        LocalDate currentDate = LocalDate.of(2023, 12, 26);

        assertThat(eventStrategy.isApplicable(currentDate, order)).isFalse();
    }

    private static class EventStrategyMock extends EventStrategy {
        public EventStrategyMock(LocalDate startDate, LocalDate endDate) {
            super(startDate, endDate);
        }

        @Override
        public EventResult applyEvent(LocalDate currentDate, Order order) {
            return null;
        }
    }
}
