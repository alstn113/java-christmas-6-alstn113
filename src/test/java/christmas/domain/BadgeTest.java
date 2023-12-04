package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BadgeTest {

    @Test
    void 총_혜택_금액에_따라_Badge를_반환한다() {
        Badge santaBadge = Badge.SANTA;
        Badge treeBadge = Badge.TREE;
        Badge starBadge = Badge.STAR;
        Badge noneBadge = Badge.NONE;

        assertThat(Badge.of(25_000)).isEqualTo(santaBadge);
        assertThat(Badge.of(15_000)).isEqualTo(treeBadge);
        assertThat(Badge.of(7_000)).isEqualTo(starBadge);
        assertThat(Badge.of(0)).isEqualTo(noneBadge);
    }

}