package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("GiftEventStrategy 클래스")
class GiftEventStrategyTest {


    @Test
    @DisplayName("12월 1일 ~ 12월 31일이 아닌 경우 null을 반환한다.")
    void applyDiscountTest1() {
        GiftEventStrategy strategy = new GiftEventStrategy();
        Order order = new Order(List.of(
                new OrderItem("양송이수프", 2),
                new OrderItem("초코케이크", 2),
                new OrderItem("제로콜라", 3)
        ));
        LocalDate date1 = LocalDate.of(2023, 11, 30);
        LocalDate date2 = LocalDate.of(2024, 1, 1);

        assertThat(strategy.applyEventIfApplicable(date1, order).getGift()).isEmpty();
        assertThat(strategy.applyEventIfApplicable(date2, order).getGift()).isEmpty();
    }

    @Test
    @DisplayName("12월 1일 ~ 12월 31일이고, 120,000원 미만 주문한 경우 null을 반환한다.")
    void applyDiscountTest2() {
        GiftEventStrategy strategy = new GiftEventStrategy();
        List<OrderItem> orderItems = List.of(
                new OrderItem("양송이수프", 2),
                new OrderItem("초코케이크", 2),
                new OrderItem("제로콜라", 3)
        );
        Order order = new Order(orderItems);
        LocalDate date1 = LocalDate.of(2023, 12, 1); // 금요일
        LocalDate date2 = LocalDate.of(2023, 12, 8); // 금요일
        LocalDate date3 = LocalDate.of(2023, 12, 31); // 토요일

        assertThat(strategy.applyEventIfApplicable(date1, order).getGift()).isEmpty();
        assertThat(strategy.applyEventIfApplicable(date2, order).getGift()).isEmpty();
        assertThat(strategy.applyEventIfApplicable(date3, order).getGift()).isEmpty();
    }

    @Test
    @DisplayName("12월 1일 ~ 12월 31일이고, 120,000원 이상 주문한 경우 샴페인 1개를 증정한다.")
    void applyDiscountTest3() {
        GiftEventStrategy strategy = new GiftEventStrategy();
        List<OrderItem> orderItems = List.of(
                new OrderItem("양송이수프", 2),
                new OrderItem("초코케이크", 2),
                new OrderItem("티본스테이크", 2),
                new OrderItem("크리스마스파스타", 1),
                new OrderItem("제로콜라", 3)
        );
        Order order = new Order(orderItems);
        LocalDate date = LocalDate.of(2023, 12, 5); // 화요일
        EventResult result = strategy.applyEventIfApplicable(date, order);
        assertThat(result.getDiscountAmount()).isZero();
        assertThat(result.getGift()).isPresent().isNotNull();
    }
}