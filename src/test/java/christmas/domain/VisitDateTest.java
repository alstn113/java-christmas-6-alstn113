package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 15, 30, 31})
    void day를_입력하면_2023년_12월_day일_LocalDate를_반환한다(int day) {
        assertThat(new VisitDate(day).getDate())
                .isEqualTo(LocalDate.of(2023, 12, day));
    }


    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 0, 32, 100})
    void 날짜는_1부터_31까지만_입력할_수_있다(int day) {
        assertThatThrownBy(() -> new VisitDate(day))
                .isInstanceOf(IllegalArgumentException.class);
    }

}