package christmas.dto.response;

import christmas.domain.event.EventType;
import java.util.LinkedHashMap;
import java.util.Map;

public record BenefitsResponse(Map<String, Integer> benefitsResponse) {
    public static BenefitsResponse from(Map<EventType, Integer> benefits) {
        Map<String, Integer> benefitsResponse = new LinkedHashMap<>();
        benefits.forEach((key, value) -> benefitsResponse.put(key.getName(), value));
        return new BenefitsResponse(benefitsResponse);
    }
}
