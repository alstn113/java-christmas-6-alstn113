package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import java.time.LocalDate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ExpectedVisitDateTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32, 100})
    void 유효하지_않은_날짜(int date) {
        assertThatThrownBy(() -> new ExpectedVisitDate(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(new InvalidInputException(ErrorMessage.INVALID_DATE).getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 15, 25, 31})
    void 유효한_날짜(int date) {
        ExpectedVisitDate expectedVisitDate = new ExpectedVisitDate(date);
        assertThat(expectedVisitDate.getDate()).isEqualTo(LocalDate.of(2023, 12, date));
    }

}