package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GiftEventStrategyTest {
    private static GiftEventStrategy strategy;
    private static Order order;

    @BeforeAll
    static void setUp() {
        List<OrderItem> orderItems = List.of(
                new OrderItem("티본스테이크", 2),
                new OrderItem("초코케이크", 5),
                new OrderItem("아이스크림", 3),
                new OrderItem("제로콜라", 3)
        );

        strategy = new GiftEventStrategy();
        order = new Order(orderItems);
    }

    @Test
    void applyEventIfApplicable메서드는_할인_전_총주문_금액이_12만_원_이상일_때_샴페인_1개_증정한다() {
        LocalDate currentDate = LocalDate.of(2023, 12, 5);

        final EventResult result = strategy.applyEventIfApplicable(currentDate, order);

        assertThat(result.getGift())
                .isPresent()
                .get()
                .satisfies(gift -> {
                    assertThat(gift.getMenu().getViewName()).isEqualTo("샴페인");
                    assertThat(gift.getQuantity()).isEqualTo(1);
                });
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

        @Test
        void 할인_전_총주문_금액이_12만_원_이상이_아닐_경우_false를_리턴한다() {
            LocalDate currentDate = LocalDate.of(2023, 12, 5);
            Order order = new Order(
                    List.of(
                            new OrderItem("양송이수프", 2),
                            new OrderItem("초코케이크", 1),
                            new OrderItem("아이스크림", 3),
                            new OrderItem("제로콜라", 3)
                    )
            );

            final boolean result = strategy.isApplicable(currentDate, order);

            assertThat(result).isFalse();
        }

        @Test
        void 할인_전_총주문_금액이_12만_원_이상일_경우_true를_리턴한다() {
            LocalDate currentDate = LocalDate.of(2023, 12, 5);
            Order order = new Order(
                    List.of(
                            new OrderItem("티본스테이크", 2),
                            new OrderItem("초코케이크", 1),
                            new OrderItem("아이스크림", 3),
                            new OrderItem("제로콜라", 3)
                    )
            );

            final boolean result = strategy.isApplicable(currentDate, order);

            assertThat(result).isTrue();
        }
    }
}
