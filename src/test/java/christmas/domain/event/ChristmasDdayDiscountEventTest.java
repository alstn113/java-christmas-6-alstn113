package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.order.Menu;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ChristmasDdayDiscountEventTest {
    @ParameterizedTest
    @ValueSource(ints = {26, 27, 30, 31})
    void 이벤트_기간이_아닌_경우(int day) {
        ChristmasDdayDiscountEvent event = new ChristmasDdayDiscountEvent();
        LocalDate date = LocalDate.of(2023, 12, day);
        OrderItem orderItem = new OrderItem(Menu.BBQ_RIB, 1);
        Order order = new Order(List.of(orderItem));

        assertThat(event.isApplicable(date, order)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1000", "2,1100", "3,1200", "25,3400"})
    void 크리스마스_디데이_할인_금액_계산(int day, int expectedDiscountPrice) {
        ChristmasDdayDiscountEvent event = new ChristmasDdayDiscountEvent();
        LocalDate date = LocalDate.of(2023, 12, day);
        OrderItem orderItem = new OrderItem(Menu.BBQ_RIB, 1);
        Order order = new Order(List.of(orderItem));

        int discountPrice = event.applyEvent(date, order).getDiscountPrice();
        assertThat(discountPrice).isEqualTo(expectedDiscountPrice);
    }
}