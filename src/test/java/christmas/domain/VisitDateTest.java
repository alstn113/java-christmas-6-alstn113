package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("VisitDate 클래스 테스트")
class VisitDateTest {
    @DisplayName("생성자 테스트")
    @Test
    void constructor() {
        int day = 1;
        assertThat(new VisitDate(day).getDate())
                .isEqualTo(LocalDate.of(2023, 12, day));
    }

    @Test
    @DisplayName("날짜가 같은지 확인")
    void isSameDate() {
        LocalDate start = LocalDate.of(2023, 12, 1);
        LocalDate end = LocalDate.of(2023, 12, 5);
        LocalDate current = LocalDate.of(2023, 12, 6);
        assertThat(isApplicable(current, start, end)).isTrue();
    }

    private boolean isApplicable(LocalDate currentDate, LocalDate startDate, LocalDate endDate) {
        return !currentDate.isBefore(startDate) && !currentDate.isAfter(endDate);
    }

    @Nested
    @DisplayName("예외 테스트")
    class ExceptionTest {
        @DisplayName("1보다 작은 날짜 입력 시 예외 발생")
        @Test
        void lessThanOne() {
            assertThatThrownBy(() -> new VisitDate(0))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("31보다 큰 날짜 입력 시 예외 발생")
        @Test
        void moreThanThirtyOne() {
            assertThatThrownBy(() -> new VisitDate(32))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}