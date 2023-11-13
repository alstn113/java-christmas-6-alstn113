package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class OrderItemTest {
    @ParameterizedTest
    @CsvSource(value = {"양송이수프, 2, 12000", "티본스테이크, 3, 165000", "레드와인, 1, 60000"})
    void getTotalPrice메서드는_주문_항목의_가격을_반환한다(String menuName, int quantity, int totalPrice) {
        OrderItem orderItem = new OrderItem(menuName, quantity);
        assertThat(orderItem.getTotalPrice()).isEqualTo(totalPrice);
    }

    @Test
    void isSameCategory메서드는_주문_항목의_카테고리를_반환한다() {
        OrderItem orderItem = new OrderItem("타파스", 1);
        assertThat(orderItem.isSameCategory(Category.APPETIZER)).isTrue();
    }

    @Test
    void getMenu메서드는_주문_항목의_메뉴를_반환한다() {
        OrderItem orderItem = new OrderItem("레드와인", 1);
        assertThat(orderItem.getMenu()).extracting(Menu::getViewName, Menu::getPrice)
                .containsExactly("레드와인", 60000);
    }

    @Nested
    class 예외_테스트 {
        @ParameterizedTest
        @ValueSource(ints = {-100, -1, 0})
        void 주문_수량이_1보다_작을_경우_예외가_발생한다(int quantity) {
            assertThatThrownBy(() -> new OrderItem("양송이수프", quantity))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest
        @ValueSource(strings = {"존재하지 않는 메뉴", "unknown"})
        void 존재하지_않는_메뉴일_경우_예외가_발생한다(String menuName) {
            assertThatThrownBy(() -> new OrderItem(menuName, 1))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
