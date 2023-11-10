package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Order 클래스 테스트")
class OrderTest {
    @DisplayName("totalPrice 메서드는 주문 항목들의 총 가격을 반환한다.")
    @Test
    void testTotalPrice() {
        // given
        Menu menu1 = new Menu("아메리카노", 3000);
        Menu menu2 = new Menu("카페라떼", 4000);
        OrderItem orderItem1 = new OrderItem(menu1, 2);
        OrderItem orderItem2 = new OrderItem(menu2, 5);

        Order order = new Order(List.of(orderItem1, orderItem2));

        // when
        int totalPrice = order.totalPrice();

        // then
        assertThat(totalPrice).isEqualTo(26000);
    }

}