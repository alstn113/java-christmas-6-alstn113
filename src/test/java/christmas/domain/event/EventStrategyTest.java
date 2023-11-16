package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EventStrategyTest {
    private static class SampleEventStrategy extends EventStrategy {
        private static final LocalDate startDate = LocalDate.of(2023, 12, 5);
        private static final LocalDate endDate = LocalDate.of(2023, 12, 25);

        protected SampleEventStrategy() {
            super(startDate, endDate);
        }

        @Override
        protected EventResult applyEvent(LocalDate currentDate, Order order) {
            return new EventResult(1000, new OrderItem("샴페인", 1));
        }
    }

    @Nested
    class applyEventIfApplicable메서드_테스트 {
        @Test
        void applyEventIfApplicable메서드는_이벤트에_해당될_경우_값이_있는_EventResult를_반환한다() {
            EventStrategy eventStrategy = new SampleEventStrategy();
            Order order = new Order(List.of(
                    new OrderItem("샴페인", 1),
                    new OrderItem("티본스테이크", 1)
            ));
            LocalDate currentDate = LocalDate.of(2023, 12, 10);

            EventResult result = eventStrategy.applyEventIfApplicable(currentDate, order);

            assertThat(result.getDiscountAmount()).isEqualTo(1000);
            assertThat(result.getGift())
                    .isPresent()
                    .get()
                    .isEqualTo(new OrderItem("샴페인", 1));
        }

        @Test
        void applyEventIfApplicable메서드는_이벤트에_해당되지_않을_경우_값이_없는_EventResult를_반환한다() {
            EventStrategy eventStrategy = new SampleEventStrategy();
            Order order = new Order(List.of(
                    new OrderItem("샴페인", 1),
                    new OrderItem("티본스테이크", 1)
            ));
            LocalDate currentDate = LocalDate.of(2023, 12, 31);

            EventResult result = eventStrategy.applyEventIfApplicable(currentDate, order);

            assertThat(result.getDiscountAmount()).isZero();
            assertThat(result.getGift()).isEmpty();
        }

        @Nested
        class isApplicable메서드_테스트 {
            @ParameterizedTest
            @ValueSource(ints = {3, 4, 26, 27})
            void isApplicable메서드는_이벤트_진행_중이_아니면_false를_리턴한다(int day) {
                EventStrategy eventStrategy = new SampleEventStrategy();
                Order order = new Order(List.of(
                        new OrderItem("샴페인", 1),
                        new OrderItem("티본스테이크", 1),
                        new OrderItem("초코케이크", 1),
                        new OrderItem("레드와인", 1)));
                LocalDate currentDate = LocalDate.of(2023, 12, day);
                boolean result = eventStrategy.isApplicable(currentDate, order);

                assertThat(result).isFalse();
            }

            @Test
            void isApplicable메서드는_총_금액이_10000원을_넘지_않으면_false를_리턴한다() {
                EventStrategy eventStrategy = new SampleEventStrategy();
                Order order = new Order(List.of(
                        new OrderItem("아이스크림", 1)
                ));

                boolean result = eventStrategy.isApplicable(LocalDate.of(2023, 12, 5), order);

                assertThat(result).isFalse();
            }

        }
    }
}
