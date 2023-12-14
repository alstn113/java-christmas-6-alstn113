package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import java.util.List;
import org.junit.jupiter.api.Test;

class OrderTest {
    @Test
    void 중복된_메뉴가_있을_경우_예외() {
        OrderItem orderItem1 = new OrderItem(Menu.CAESAR_SALAD, 1);
        OrderItem orderItem2 = new OrderItem(Menu.T_BONE_STEAK, 1);
        OrderItem orderItem3 = new OrderItem(Menu.CAESAR_SALAD, 1);
        List<OrderItem> orderItems = List.of(orderItem1, orderItem2, orderItem3);
        assertThatThrownBy(() -> new Order(orderItems))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(new InvalidInputException(ErrorMessage.INVALID_ORDER).getMessage());
    }
}