package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {
    @ParameterizedTest
    @ValueSource(strings = {"일", "이이", "삼삼삼", "사사사사"})
    void 존재하지_않는_메뉴(String menuName) {
        assertThatThrownBy(() -> Menu.findByMenuName(menuName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드", "티본스테이크"})
    void 존재하는_메뉴(String menuName) {
        Menu menu = Menu.findByMenuName(menuName);
        assertThat(menu.getViewName()).isEqualTo(menuName);
    }
}