package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.order.OrderItem;
import org.junit.jupiter.api.Test;

class EventResultTest {

    @Test
    void EventResult를_생성한다() {
        EventResult result = new EventResult(1000, new OrderItem("샴페인", 1));

        assertThat(result.getDiscountAmount()).isEqualTo(1000);
        assertThat(result.getGift())
                .isPresent()
                .get().isEqualTo(new OrderItem("샴페인", 1));
    }

    @Test
    void empty메서드로_EventResult를_생성한다() {
        EventResult result = EventResult.empty();

        assertThat(result.getDiscountAmount()).isZero();
        assertThat(result.getGift()).isEmpty();
    }
}
