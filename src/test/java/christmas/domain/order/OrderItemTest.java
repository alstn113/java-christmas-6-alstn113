package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("OrderItem 클래스 테스트")
class OrderItemTest {
    @DisplayName("price 메서드는 주문 항목의 가격을 반환한다.")
    @Test
    void testPrice() {
        OrderItem orderItem = new OrderItem("양송이수프", 2);
        assertThat(orderItem.getTotalPrice()).isEqualTo(12000);
    }

    @DisplayName("category 메서드는 주문 항목의 카테고리를 반환한다.")
    @Test
    void testCategory() {
        OrderItem orderItem = new OrderItem("타파스", 1);
        assertThat(orderItem.isSameCategory(Category.APPETIZER)).isTrue();
    }

    @DisplayName("menu 메서드는 주문 항목의 메뉴를 반환한다.")
    @Test
    void testMenu() {
        OrderItem orderItem = new OrderItem("레드와인", 1);
        assertThat(orderItem.getMenu()).extracting(Menu::getViewName, Menu::getPrice)
                .containsExactly("레드와인", 60000);
    }

    @DisplayName("OrderItem 예외 테스트")
    @Nested
    class OrderItemExceptionTest {
        @DisplayName("주문 수량이 1 미만일 경우 예외가 발생한다.")
        @Test
        void testQuantity() {
            assertThatThrownBy(() -> new OrderItem("양송이수프", 0))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("존재하지 않는 메뉴일 경우 예외가 발생한다.")
        @Test
        void testIsExistMenu() {
            assertThatThrownBy(() -> new OrderItem("존재하지 않는 메뉴", 1))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
