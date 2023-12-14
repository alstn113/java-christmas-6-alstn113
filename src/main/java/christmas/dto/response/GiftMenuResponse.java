package christmas.dto.response;

import christmas.domain.OrderItem;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public record GiftMenuResponse(Map<String, Integer> giftMenus) {
    public static GiftMenuResponse from(List<OrderItem> giftMenus) {
        Map<String, Integer> giftMenuResponse = new LinkedHashMap<>();

        for (OrderItem giftMenu : giftMenus) {
            String menuName = giftMenu.menu().getName();
            int quantity = giftMenu.quantity();
            giftMenuResponse.put(menuName, quantity);
        }

        return new GiftMenuResponse(giftMenuResponse);
    }
}
