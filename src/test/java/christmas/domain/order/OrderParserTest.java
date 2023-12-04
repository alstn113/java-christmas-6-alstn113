package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.InvalidInputException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderParserTest {
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1,제로콜라-2,타파스-1"})
    void 정상적인_주문(String input) {
        Order order = OrderParser.parseInputToOrder(input);
        assertThat(order.getOrderItems()).containsExactly(
                new OrderItem(Menu.T_BONE_STEAK, 1),
                new OrderItem(Menu.ZERO_COLA, 2),
                new OrderItem(Menu.TAPAS, 1)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "티본스테이크--1", "타파스-1-1", "타파스-1,,제로콜라-2, 타파스1"})
    void 옳바르지_않는_형식(String input) {
        assertThatThrownBy(() -> OrderParser.parseInputToOrder(input))
                .isInstanceOf(InvalidInputException.class);
    }
}