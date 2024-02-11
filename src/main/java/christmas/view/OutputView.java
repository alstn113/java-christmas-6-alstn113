package christmas.view;

import christmas.dto.response.EventBenefitsPreviewResponse;

public interface OutputView {
    void printWelcomeMessage();

    void printEventBenefitsPreview(EventBenefitsPreviewResponse eventBenefitsPreviewResponse);
}
