package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.order.Category;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Order 클래스 테스트")
class OrderTest {
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

    @Test
    @DisplayName("카테고리에 따른 총 주문 개수 계산")
    void testGetQuantityByCategory() {
        List<OrderItem> orderItems = List.of(
                new OrderItem("티본스테이크", 3),
                new OrderItem("아이스크림", 2),
                new OrderItem("초코케이크", 5),
                new OrderItem("레드와인", 2)
        );
        Order order = new Order(orderItems);
        assertThat(order.getQuantityByCategory(Category.DRINK)).isEqualTo(2);
        assertThat(order.getQuantityByCategory(Category.DESSERT)).isEqualTo(7);
        assertThat(order.getQuantityByCategory(Category.MAIN)).isEqualTo(3);
    }

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
        @DisplayName("중복된 메뉴가 존재하는 경우 예외 발생")
        void validateDuplicateMenu() {
            List<OrderItem> orderItems = List.of(
                    new OrderItem("티본스테이크", 3),
                    new OrderItem("초코케이크", 5),
                    new OrderItem("티본스테이크", 2)
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
}
