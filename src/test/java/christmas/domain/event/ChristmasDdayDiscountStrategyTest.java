package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChristmasDdayDiscountStrategyTest {
    private static ChristmasDdayDiscountStrategy strategy;
    private static Order order;

    @BeforeAll
    static void setUp() {
        List<OrderItem> orderItems = List.of(
                new OrderItem("티본스테이크", 2),
                new OrderItem("초코케이크", 5),
                new OrderItem("아이스크림", 3),
                new OrderItem("제로콜라", 3)
        );

        strategy = new ChristmasDdayDiscountStrategy();
        order = new Order(orderItems);
    }

    @ParameterizedTest
    @CsvSource(value = {"1, 1000", "2, 1100", "3, 1200", "15, 2400", "23, 3200", "24, 3300", "25, 3400"})
    void applyEventIfApplicable메서드는_1000원으로_시작하여_크리스마스가_다가올수록_날마다_할인_금액이_100원씩_증가한다(int day, int discountAmount) {
        LocalDate currentDate = LocalDate.of(2023, 12, day);

        final EventResult result = strategy.applyEventIfApplicable(currentDate, order);

        assertThat(result.getDiscountAmount()).isEqualTo(discountAmount);
    }

    @Nested
    class isApplicable메서드_테스트 {
        @Test
        void 현재_날짜가_12월_1일_이상_12월_25일_이하가_아닌_경우_false를_리턴한다() {
            LocalDate beforeEventDate = LocalDate.of(2023, 11, 30);
            LocalDate afterEventDate = LocalDate.of(2023, 12, 26);

            final boolean beforeEventResult = strategy.isApplicable(beforeEventDate, order);
            final boolean afterEventResult = strategy.isApplicable(afterEventDate, order);

            assertThat(beforeEventResult).isFalse();
            assertThat(afterEventResult).isFalse();
        }
    }
}
