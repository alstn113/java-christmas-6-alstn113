package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ChristmasDdayDiscountStrategy 클래스")
class ChristmasDdayDiscountStrategyTest {

    private static ChristmasDdayDiscountStrategy strategy;
    private static Order order;

    @BeforeAll
    static void setUp() {
        strategy = new ChristmasDdayDiscountStrategy();
        List<OrderItem> orderItems = List.of(
                new OrderItem("티본스테이크", 2),
                new OrderItem("초코케이크", 5),
                new OrderItem("아이스크림", 3),
                new OrderItem("제로콜라", 3)
        );
        order = new Order(orderItems);
    }


    @Test
    @DisplayName("12월 1일 ~ 12월 25일이 아닌 경우 0을 반환한다.")
    void calculateDiscountTest1() {
        LocalDate date1 = LocalDate.of(2023, 11, 30);
        LocalDate date2 = LocalDate.of(2023, 12, 26);
        LocalDate date3 = LocalDate.of(2024, 1, 1);

        assertThat(strategy.calculateDiscount(order, date1)).isZero();
        assertThat(strategy.calculateDiscount(order, date2)).isZero();
        assertThat(strategy.calculateDiscount(order, date3)).isZero();
    }

    @Test
    @DisplayName("1000원을 시작하여 1일이 지날 때마다 100원씩 할인한다.")
    void calculateDiscountTest2() {
        LocalDate date1 = LocalDate.of(2023, 12, 1);
        LocalDate date2 = LocalDate.of(2023, 12, 2);
        LocalDate date3 = LocalDate.of(2023, 12, 3);
        LocalDate date15 = LocalDate.of(2023, 12, 15);
        LocalDate date25 = LocalDate.of(2023, 12, 25);

        assertThat(strategy.calculateDiscount(order, date1)).isEqualTo(1000);
        assertThat(strategy.calculateDiscount(order, date2)).isEqualTo(1100);
        assertThat(strategy.calculateDiscount(order, date3)).isEqualTo(1200);
        assertThat(strategy.calculateDiscount(order, date15)).isEqualTo(2400);
        assertThat(strategy.calculateDiscount(order, date25)).isEqualTo(3400);
    }
}
