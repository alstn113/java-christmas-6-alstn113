package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Category 클래스 테스트")
class CategoryTest {

    @Nested
    @DisplayName("메뉴 이름으로 메뉴를 찾는다.")
    class FindMenuByNameTest {
        @DisplayName("메뉴 이름이 존재하지 않는 경우")
        @Test
        void testfindMenuByNameWithNotExistMenuName() {
            assertThat(Category.findMenuByName("초코케이크"))
                    .isNotNull()
                    .extracting(Menu::name)
                    .isEqualTo("초코케이크");

        }

        @DisplayName("메뉴 이름이 존재하는 경우")
        @Test
        void testfindMenuByNameWithExistMenuName() {
            assertThatThrownBy(() -> Category.findMenuByName("없는 메뉴"))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}