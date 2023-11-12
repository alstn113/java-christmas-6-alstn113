package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Badge Enum 테스트")
class BadgeTest {
    @DisplayName("조건에 따른 Badge 반환 테스트")
    @Test
    void getBadgeByConditionTest() {
        assertThat(Badge.getBadgeByCondition(0)).isEqualTo(Badge.NONE);
        assertThat(Badge.getBadgeByCondition(5000)).isEqualTo(Badge.STAR);
        assertThat(Badge.getBadgeByCondition(10000)).isEqualTo(Badge.TREE);
        assertThat(Badge.getBadgeByCondition(20000)).isEqualTo(Badge.SANTA);
    }

    @DisplayName("viewName 반환 테스트")
    @Test
    void getViewNameTest() {
        assertThat(Badge.NONE.getViewName()).isEqualTo("없음");
        assertThat(Badge.STAR.getViewName()).isEqualTo("별");
        assertThat(Badge.TREE.getViewName()).isEqualTo("트리");
        assertThat(Badge.SANTA.getViewName()).isEqualTo("산타");
    }

}