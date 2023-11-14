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
import org.junit.jupiter.params.provider.ValueSource;

class WeekdayDiscountStrategyTest {

    private static WeekdayDiscountStrategy strategy;
    private static Order order;

    @BeforeAll
    static void setUp() {
        List<OrderItem> orderItems = List.of(
                new OrderItem("티본스테이크", 2),
                new OrderItem("초코케이크", 5),
                new OrderItem("아이스크림", 3),
                new OrderItem("제로콜라", 3)
        );

        strategy = new WeekdayDiscountStrategy();
        order = new Order(orderItems);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 7, 13, 21, 26})
    void applyEventIfApplicable메서드는_평일일_경우_디저트_개수당_2023원을_할인한다(int day) {
        LocalDate currentDate = LocalDate.of(2023, 12, day);

        final EventResult result = strategy.applyEventIfApplicable(currentDate, order);

        assertThat(result.getDiscountAmount()).isEqualTo(2023 * 8);
    }

    @Nested
    class isApplicable메서드_테스트 {
        @Test
        void 현재_날짜가_12월_1일_이상_12월_31일_이하가_아닌_경우_false를_리턴한다() {
            LocalDate beforeEventDate = LocalDate.of(2023, 11, 30); // 12월 1일 미만
            LocalDate afterEventDate = LocalDate.of(2024, 1, 1); // 12월 31일 초과

            final boolean beforeEventResult = strategy.isApplicable(beforeEventDate, order);
            final boolean afterEventResult = strategy.isApplicable(afterEventDate, order);

            assertThat(beforeEventResult).isFalse();
            assertThat(afterEventResult).isFalse();
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 9, 15, 23, 29})
        void 평일이_아닐_경우_false를_리턴한다(int day) {
            LocalDate currentDate = LocalDate.of(2023, 12, day);

            final boolean result = strategy.isApplicable(currentDate, order);

            assertThat(result).isFalse();
        }

        @Test
        void 디저트의_개수가_0개일_경우_false를_리턴한다() {
            LocalDate currentDate = LocalDate.of(2023, 12, 5); // 화요일
            Order order = new Order(List.of(
                    new OrderItem("티본스테이크", 2),
                    new OrderItem("제로콜라", 3)
            ));

            final boolean result = strategy.isApplicable(currentDate, order);

            assertThat(result).isFalse();
        }
    }
}
