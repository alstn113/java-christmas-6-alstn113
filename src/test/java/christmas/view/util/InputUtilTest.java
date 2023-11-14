package christmas.view.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.order.OrderItem;
import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class InputUtilTest {
    @Nested
    class parseInputToOrderItems메서드_테스트 {
        @Test
        void 결과로_OrderItem_List를_반환한다() {
            String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

            List<OrderItem> result = InputUtil.parseInputToOrderItems(input);

            assertThat(result).hasSize(4);
            List<String> menuNames = List.of("티본스테이크", "바비큐립", "초코케이크", "제로콜라");
            List<Integer> quantities = List.of(1, 1, 2, 1);

            for (int i = 0; i < result.size(); i++) {
                assertThat(result.get(i).getMenu().getViewName()).isEqualTo(menuNames.get(i));
                assertThat(result.get(i).getQuantity()).isEqualTo(quantities.get(i));
            }
        }

        @ParameterizedTest
        @ValueSource(strings = {"해산물파스타-", "스프-2,타파스-1,샐러드,", "해산물파스타-0,레드와인-2,",
                "아이스크림-2,티본스테이크-1,", "-1,초코케이크-1", "해산물파스타-a", " "})
        void 옳바르지_않은_형식은_예외를_일으킨다(String input) {
            assertThatThrownBy(() -> InputUtil.parseInputToOrderItems(input))
                    .isInstanceOf(InvalidInputException.class)
                    .hasMessageContaining(ErrorMessage.INVALID_ORDER.getMessage());
        }

    }

    @Nested
    class parseInputToInt메서드_테스트 {
        @ParameterizedTest
        @CsvSource(value = {"-1, -1", "0, 0", "42, 42"})
        void 입력을_숫자로_변환한다(String input, int expected) {
            int result = InputUtil.parseInputToInt(input);

            assertThat(result).isEqualTo(expected);
        }

        @ParameterizedTest
        @ValueSource(strings = {"not_a_number", " ", "숫자", "!@#"})
        void 숫자로_변환할_수_없는_경우_예외를_일으킨다(String input) {
            assertThatThrownBy(() -> InputUtil.parseInputToInt(input))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
