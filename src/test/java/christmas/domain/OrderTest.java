package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Order 클래스 테스트")
class OrderTest {
    @Nested
    @DisplayName("주문 유효성 검증")
    class OrderValidation {

        @Test
        @DisplayName("주문 개수가 20개를 초과하는 경우 예외 발생")
        void validateMaxMenuQuantity() {
            List<OrderItem> orderItems = List.of(
                    new OrderItem("티본스테이크", 11),
                    new OrderItem("초코케이크", 6),
                    new OrderItem("레드와인", 5)
            );
            assertThatThrownBy(() -> new Order(orderItems))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("음료만 주문한 경우 예외 발생")
        void validateOrderNotOnlyDrink() {
            List<OrderItem> drinkItems = List.of(
                    new OrderItem("제로콜라", 1),
                    new OrderItem("레드와인", 1)
            );
            assertThatThrownBy(() -> new Order(drinkItems))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("주문 계산")
    class OrderCalculation {

        @Test
        @DisplayName("총 주문 가격 계산")
        void totalPrice() {
            List<OrderItem> orderItems = List.of(
                    new OrderItem("티본스테이크", 3),
                    new OrderItem("초코케이크", 5),
                    new OrderItem("레드와인", 2)
            );
            Order order = new Order(orderItems);
            assertThat(order.totalPrice()).isEqualTo(360000);
        }
    }
}
