package christmas.domain;

public record OrderItem(String menuName, int quantity) {

    public OrderItem {
        validate(menuName, quantity);
    }

    private void validate(String menuName, int quantity) {
        validateQuantity(quantity);
        validateIsExistMenu(menuName);
    }

    private void validateQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");
        }
    }

    private void validateIsExistMenu(String menuName) {
        Category.findMenuByName(menuName);
    }


    public int price() {
        return menu().price() * quantity;
    }

    public Category category() {
        return Category.from(menuName);
    }

    public Menu menu() {
        return Category.findMenuByName(menuName);
    }


}