package christmas.domain.order;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.order.Category;
import christmas.domain.order.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Category Enum 테스트")
class CategoryTest {

    @Test
    void testGetCategoryFromMenu() {
        Category appetizerCategory = Category.from(Menu.MUSHROOM_SOUP);
        Category mainCategory = Category.from(Menu.SEAFOOD_PASTA);
        Category dessertCategory = Category.from(Menu.ICE_CREAM);
        Category drinkCategory = Category.from(Menu.ZERO_COLA);

        assertThat(appetizerCategory).isEqualTo(Category.APPETIZER);
        assertThat(mainCategory).isEqualTo(Category.MAIN);
        assertThat(dessertCategory).isEqualTo(Category.DESSERT);
        assertThat(drinkCategory).isEqualTo(Category.DRINK);
    }
}
