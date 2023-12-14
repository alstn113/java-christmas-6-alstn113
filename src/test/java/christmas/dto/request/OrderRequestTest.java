package christmas.dto.request;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderRequestTest {
    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-2,,초코케이크-1", "", "해산물파스타2", "우유-1"})
    void 형식이_맞지_않는_경우_예외(String input) {
        assertThatThrownBy(() -> new OrderRequest(input).toOrder())
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(new InvalidInputException(ErrorMessage.INVALID_ORDER).getMessage());
    }

    @Test
    void 최대_20개를_넘은_주문일_경우_예외() {
        String input = "해산물파스타-5,초코케이크-16";
        assertThatThrownBy(() -> new OrderRequest(input).toOrder())
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(new InvalidInputException(ErrorMessage.INVALID_ORDER).getMessage());
    }

    @Test
    void 음료만_주문한_경우_예외() {
        String input = "제로콜라-1";
        assertThatThrownBy(() -> new OrderRequest(input).toOrder())
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(new InvalidInputException(ErrorMessage.INVALID_ORDER).getMessage());
    }
}