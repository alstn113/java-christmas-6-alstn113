package christmas.domain.order;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {
    @ParameterizedTest
    @EnumSource(Menu.class)
    void findMenuByName메서드는_메뉴이름를_받아서_메뉴를_반환한다(Menu menu) {
        assertThat(Menu.findMenuByName(menu.getViewName())).isEqualTo(menu);
    }

    @ParameterizedTest
    @ValueSource(strings = {"존재하지 않는 메뉴", "unknown"})
    void findMenuByName메서드는_존재하지_않는_메뉴이름를_받으면_예외를_발생한다(String menuName) {
        assertThatThrownBy(() -> Menu.findMenuByName(menuName))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

