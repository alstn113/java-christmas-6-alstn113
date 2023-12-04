package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class OrderTest {
    @Test
    void 정상_생성() {
        OrderItem orderItem1 = new OrderItem(Menu.MUSHROOM_SOUP, 2);
        OrderItem orderItem2 = new OrderItem(Menu.T_BONE_STEAK, 3);
        OrderItem orderItem3 = new OrderItem(Menu.CHOCO_CAKE, 4);
        OrderItem orderItem4 = new OrderItem(Menu.RED_WINE, 5);

        List<OrderItem> orderItems = List.of(orderItem1, orderItem2, orderItem3, orderItem4);
        Order order = new Order(orderItems);

        assertThat(order.getOrderItems()).isEqualTo(orderItems);
        assertThat(order.getTotalPrice()).isEqualTo(2 * 6_000 + 3 * 55_000 + 4 * 15_000 + 5 * 60_000);
    }

    @Test
    void 중복된_메뉴가_있는_경우_예외() {
        OrderItem orderItem1 = new OrderItem(Menu.MUSHROOM_SOUP, 2);
        OrderItem orderItem2 = new OrderItem(Menu.MUSHROOM_SOUP, 3);
        OrderItem orderItem3 = new OrderItem(Menu.CHOCO_CAKE, 4);
        OrderItem orderItem4 = new OrderItem(Menu.RED_WINE, 5);

        List<OrderItem> orderItems = List.of(orderItem1, orderItem2, orderItem3, orderItem4);

        assertThatThrownBy(() -> new Order(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 주문_수량이_20개를_넘는_경우_예외() {
        OrderItem orderItem1 = new OrderItem(Menu.MUSHROOM_SOUP, 2);
        OrderItem orderItem2 = new OrderItem(Menu.T_BONE_STEAK, 3);
        OrderItem orderItem3 = new OrderItem(Menu.CHOCO_CAKE, 4);
        OrderItem orderItem4 = new OrderItem(Menu.RED_WINE, 12);

        List<OrderItem> orderItems = List.of(orderItem1, orderItem2, orderItem3, orderItem4);

        assertThatThrownBy(() -> new Order(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 음료만_주문하는_경우_예외() {
        OrderItem orderItem1 = new OrderItem(Menu.ZERO_COLA, 2);
        OrderItem orderItem2 = new OrderItem(Menu.RED_WINE, 3);

        List<OrderItem> orderItems = List.of(orderItem1, orderItem2);

        assertThatThrownBy(() -> new Order(orderItems))
                .isInstanceOf(IllegalArgumentException.class);
    }
}