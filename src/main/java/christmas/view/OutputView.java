package christmas.view;

import christmas.dto.EventBenefitsPreviewResponse;
import christmas.dto.OrderPreviewResponse;

public interface OutputView {
    void printErrorMessage(String message);

    void printWelcomeMessage();

    void printEventBenefitPreviewMessage();

    void printOrderPreview(OrderPreviewResponse orderPreviewResponse);

    void printEventBenefitPreview(EventBenefitsPreviewResponse eventBenefitsPreviewResponse);
}
