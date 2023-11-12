package christmas.domain.event.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.domain.event.discount.SpecialDiscountStrategy;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("SpecialDiscountStrategy 클래스")
class SpecialDiscountStrategyTest {

    private static SpecialDiscountStrategy strategy;
    private static Order order;

    @BeforeAll
    static void setUp() {
        strategy = new SpecialDiscountStrategy();
        List<OrderItem> orderItems = List.of(
                new OrderItem("티본스테이크", 2),
                new OrderItem("초코케이크", 5),
                new OrderItem("아이스크림", 3),
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
    @DisplayName("12월의 일요일과 크리스마스에는 1000을 할인한다.")
    void applyDiscountTest2() {
        LocalDate date1 = LocalDate.of(2023, 12, 3);
        LocalDate date2 = LocalDate.of(2023, 12, 10);
        LocalDate date3 = LocalDate.of(2023, 12, 17);
        LocalDate date4 = LocalDate.of(2023, 12, 24);
        LocalDate date5 = LocalDate.of(2023, 12, 31);
        LocalDate christmasDate = LocalDate.of(2023, 12, 25);

        assertThat(strategy.applyDiscount(order, date1)).isEqualTo(1000);
        assertThat(strategy.applyDiscount(order, date2)).isEqualTo(1000);
        assertThat(strategy.applyDiscount(order, date3)).isEqualTo(1000);
        assertThat(strategy.applyDiscount(order, date4)).isEqualTo(1000);
        assertThat(strategy.applyDiscount(order, date5)).isEqualTo(1000);
        assertThat(strategy.applyDiscount(order, christmasDate)).isEqualTo(1000);
    }

    @Test
    @DisplayName("12월의 일요일과 크리스마스가 아닌 경우 0을 반환한다.")
    void applyDiscountTest3() {
        LocalDate date1 = LocalDate.of(2023, 12, 5); // 화요일
        LocalDate date2 = LocalDate.of(2023, 12, 18); // 월요일
        LocalDate date3 = LocalDate.of(2023, 12, 28); // 목요일

        assertThat(strategy.applyDiscount(order, date1)).isZero();
        assertThat(strategy.applyDiscount(order, date2)).isZero();
        assertThat(strategy.applyDiscount(order, date3)).isZero();
    }
}
