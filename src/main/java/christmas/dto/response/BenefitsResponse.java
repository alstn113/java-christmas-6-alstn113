package christmas.dto.response;

import christmas.domain.event.EventType;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public record BenefitsResponse(Map<String, Integer> benefits) {
    public static BenefitsResponse from(Map<EventType, Integer> benefits) {
        Map<String, Integer> benefitsResponse = new LinkedHashMap<>();

        for (Entry<EventType, Integer> entry : benefits.entrySet()) {
            String eventName = entry.getKey().getName();
            int discount = entry.getValue();
            benefitsResponse.put(eventName, discount);
        }

        return new BenefitsResponse(benefitsResponse);
    }
}
