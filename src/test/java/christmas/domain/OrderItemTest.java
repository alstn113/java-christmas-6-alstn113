package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("OrderItem 클래스 테스트")
class OrderItemTest {

    @DisplayName("price 메서드는 주문 항목의 가격을 반환한다.")
    @Test
    void testPrice() {
        OrderItem orderItem = new OrderItem("양송이수프", 2);
        assertThat(orderItem.price()).isEqualTo(12000);
    }

    @DisplayName("category 메서드는 주문 항목의 카테고리를 반환한다.")
    @Test
    void testCategory() {
        OrderItem orderItem = new OrderItem("타파스", 1);
        assertThat(orderItem.category()).isEqualTo(Category.APPETIZER);
    }

    @DisplayName("menu 메서드는 주문 항목의 메뉴를 반환한다.")
    @Test
    void testMenu() {
        OrderItem orderItem = new OrderItem("레드와인", 1);
        assertThat(orderItem.menu()).extracting(Menu::name, Menu::price)
                .containsExactly("레드와인", 60000);
    }
}
