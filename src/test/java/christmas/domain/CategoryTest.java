package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CategoryTest {
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드"})
    void 존재하는_메뉴(String menuName) {
        assertThat(Category.findByMenuName(menuName)).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = {"고구마", "호박", "감자"})
    void 존재하지_않는_메뉴(String menuName) {
        assertThatThrownBy(() -> Category.findByMenuName(menuName));
    }
}