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

class SpecialDiscountStrategyTest {

    private static SpecialDiscountStrategy strategy;
    private static Order order;

    @BeforeAll
    static void setUp() {
        List<OrderItem> orderItems = List.of(
                new OrderItem("티본스테이크", 2),
                new OrderItem("초코케이크", 5),
                new OrderItem("아이스크림", 3),
                new OrderItem("제로콜라", 3)
        );

        strategy = new SpecialDiscountStrategy();
        order = new Order(orderItems);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void applyEventIfApplicable메서드는_달력에_별이_있을_경우_1000원을_할인한다(int day) {
        LocalDate currentDate = LocalDate.of(2023, 12, day);

        final EventResult result = strategy.applyEventIfApplicable(currentDate, order);

        assertThat(result.getDiscountAmount()).isEqualTo(1000);
    }

    @Nested
    class isApplicable메서드_테스트 {
        @Test
        void 현재_날짜가_12월_1일_이상_12월_31일_이하가_아닌_경우_false를_리턴한다() {
            LocalDate beforeEventDate = LocalDate.of(2023, 11, 30);
            LocalDate afterEventDate = LocalDate.of(2024, 1, 1);

            final boolean beforeEventResult = strategy.isApplicable(beforeEventDate, order);
            final boolean afterEventResult = strategy.isApplicable(afterEventDate, order);

            assertThat(beforeEventResult).isFalse();
            assertThat(afterEventResult).isFalse();
        }

        @ParameterizedTest
        @ValueSource(ints = {4, 12, 16, 20, 22, 28})
        void 달력에_별이_없을_경우_false를_리턴한다(int day) {
            LocalDate currentDate = LocalDate.of(2023, 12, day);

            final boolean result = strategy.isApplicable(currentDate, order);

            assertThat(result).isFalse();
        }

        @ParameterizedTest
        @ValueSource(ints = {3, 10, 17, 24, 25, 31})
        void 달력에_별이_있을_경우_true를_리턴한다(int day) {
            LocalDate currentDate = LocalDate.of(2023, 12, day);

            final boolean result = strategy.isApplicable(currentDate, order);

            assertThat(result).isTrue();
        }
    }
}
