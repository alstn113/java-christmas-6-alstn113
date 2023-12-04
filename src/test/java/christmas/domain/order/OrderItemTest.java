package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.order.Menu;
import christmas.domain.order.OrderItem;
import christmas.exception.InvalidInputException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderItemTest {
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -100})
    void 수량이_1미만일_경우_예외(int quantity) {
        assertThatThrownBy(() -> new OrderItem(Menu.MUSHROOM_SOUP, quantity))
                .isInstanceOf(InvalidInputException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 100})
    void 수량이_1이상일_경우_정상(int quantity) {
        new OrderItem(Menu.MUSHROOM_SOUP, quantity);
    }
}