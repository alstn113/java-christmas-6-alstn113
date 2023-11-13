package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BadgeTest {
    private static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of(0, Badge.NONE),
                Arguments.of(5000, Badge.STAR),
                Arguments.of(10000, Badge.TREE),
                Arguments.of(20000, Badge.SANTA),
                Arguments.of(25000, Badge.SANTA),
                Arguments.of(-5000, Badge.NONE)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    void 조건에_따른_Badge를_반환한다(int condition, Badge badge) {
        assertThat(Badge.getBadgeByCondition(condition)).isEqualTo(badge);
    }

}