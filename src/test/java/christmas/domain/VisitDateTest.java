package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("VisitDate Record 테스트")
class VisitDateTest {
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