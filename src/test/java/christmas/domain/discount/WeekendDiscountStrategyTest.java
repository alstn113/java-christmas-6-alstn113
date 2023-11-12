package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("WeekendDiscountStrategy 클래스")
class WeekendDiscountStrategyTest {

    private static WeekendDiscountStrategy strategy;
    private static Order order;

    @BeforeAll
    static void setUp() {
        strategy = new WeekendDiscountStrategy();
        List<OrderItem> orderItems = List.of(
                new OrderItem("양송이수프", 2),
                new OrderItem("티본스테이크", 2),
                new OrderItem("크리스마스파스타", 1),
                new OrderItem("초코케이크", 2),
                new OrderItem("제로콜라", 3)
        );
        order = new Order(orderItems);
    }


    @Test
    @DisplayName("12월 1일 ~ 12월 31일이 아닌 경우 0을 반환한다.")
    void applyDiscountTest1() {
        LocalDate date1 = LocalDate.of(2023, 11, 30);
        LocalDate date2 = LocalDate.of(2024, 1, 1);

        assertThat(strategy.applyDiscount(order, date1)).isZero();
        assertThat(strategy.applyDiscount(order, date2)).isZero();
    }

    @Test
    @DisplayName("12월 1일 ~ 12월 31일이고, 평일인 경우(금요일, 토요일이 아닌 경우) 0을 반환한다.")
    void applyDiscountTest2() {
        LocalDate date1 = LocalDate.of(2023, 12, 5); // 화요일
        LocalDate date2 = LocalDate.of(2023, 12, 18); // 월요일
        LocalDate date3 = LocalDate.of(2023, 12, 28); // 목요일

        assertThat(strategy.applyDiscount(order, date1)).isZero();
        assertThat(strategy.applyDiscount(order, date2)).isZero();
        assertThat(strategy.applyDiscount(order, date3)).isZero();
    }

    @Test
    @DisplayName("12월 1일 ~ 12월 31일이고, 주말이 아닌 경우 디저트 개수당 2023원을 할인한다.")
    void applyDiscountTest3() {
        LocalDate date1 = LocalDate.of(2023, 12, 1); // 금요일
        LocalDate date2 = LocalDate.of(2023, 12, 8); // 금요일
        LocalDate date3 = LocalDate.of(2023, 12, 30); // 토요일

        assertThat(strategy.applyDiscount(order, date1)).isEqualTo(6069);
        assertThat(strategy.applyDiscount(order, date2)).isEqualTo(6069);
        assertThat(strategy.applyDiscount(order, date3)).isEqualTo(6069);
    }
}
