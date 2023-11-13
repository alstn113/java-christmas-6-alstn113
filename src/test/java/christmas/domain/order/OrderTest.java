package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class OrderTest {
    @Test
    void totalPrice메서드는_총_주문_가격을_반환한다() {
        List<OrderItem> orderItems = List.of(
                new OrderItem("티본스테이크", 3),
                new OrderItem("초코케이크", 5),
                new OrderItem("레드와인", 2)
        );
        Order order = new Order(orderItems);
        assertThat(order.totalPrice()).isEqualTo(360000);
    }

    @Test
    void getQuantityByCategory는_카테고리에_따른_총_주문_개수를_반환한다() {
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
    class 예외_테스트 {
        @Test
        void 주문_개수가_20개를_초과하는_경우_예외가_발생한다() {
            List<OrderItem> orderItems = List.of(
                    new OrderItem("티본스테이크", 10),
                    new OrderItem("초코케이크", 6),
                    new OrderItem("레드와인", 5)
            );
            assertThatThrownBy(() -> new Order(orderItems))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 중복된_메뉴가_존재하는_경우_예외가_발생한다() {
            List<OrderItem> orderItems = List.of(
                    new OrderItem("티본스테이크", 3),
                    new OrderItem("초코케이크", 5),
                    new OrderItem("티본스테이크", 2)
            );
            assertThatThrownBy(() -> new Order(orderItems))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 음료만_주문한_경우_예외가_발생한다() {
            List<OrderItem> drinkItems = List.of(
                    new OrderItem("제로콜라", 1)
            );
            assertThatThrownBy(() -> new Order(drinkItems))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
