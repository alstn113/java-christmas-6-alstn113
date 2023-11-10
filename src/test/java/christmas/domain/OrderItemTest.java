package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("OrderItem 클래스 테스트")
class OrderItemTest {
    @DisplayName("price 메서드는 메뉴의 가격과 수량을 곱한 값을 반환한다.")
    @Test
    void testPrice() {
        // given
        Menu menu = new Menu("아메리카노", 3000);
        OrderItem orderItem = new OrderItem(menu, 2);

        // when
        int price = orderItem.price();

        // then
        assertThat(price).isEqualTo(6000);
    }

}