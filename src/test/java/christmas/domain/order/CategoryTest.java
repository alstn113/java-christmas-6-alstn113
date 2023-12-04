package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.order.Category;
import christmas.domain.order.Menu;
import org.junit.jupiter.api.Test;

class CategoryTest {
    @Test
    void 메뉴의_카테고리를_찾는다() {
        Menu menu1 = Menu.MUSHROOM_SOUP;
        Menu menu2 = Menu.CHRISTMAS_PASTA;
        Menu menu3 = Menu.ZERO_COLA;
        assertThat(Category.findByMenu(menu1)).isEqualTo(Category.APPETIZER);
        assertThat(Category.findByMenu(menu2)).isEqualTo(Category.MAIN_COURSE);
        assertThat(Category.findByMenu(menu3)).isEqualTo(Category.DRINK);
    }
}