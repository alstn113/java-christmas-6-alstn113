package christmas.domain.order;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class CategoryTest {
    @ParameterizedTest
    @EnumSource(Category.class)
    void from메서드는_메뉴를_받아서_메뉴의_카테고리를_반환한다(Category category) {
        for (Menu menu : category.getMenus()) {
            assertThat(Category.from(menu)).isEqualTo(category);
        }
    }
}
