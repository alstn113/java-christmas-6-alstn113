package christmas.controller;

import christmas.domain.ExpectedVisitDate;
import christmas.domain.OrderResult;
import christmas.domain.event.EventType;
import christmas.domain.order.Order;
import christmas.dto.request.ExpectedVisitDateRequest;
import christmas.dto.request.OrderRequest;
import christmas.dto.response.EventBenefitsPreviewResponse;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.util.InputUtil;
import java.util.List;

public class EventPlannerController implements Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final List<EventType> eventTypes;

    public EventPlannerController(InputView inputView, OutputView outputView,
                                  List<EventType> eventTypes) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.eventTypes = eventTypes;
    }

    @Override
    public void run() {
        outputView.printWelcomeMessage();
        ExpectedVisitDate expectedVisitDate = readExpectedVisitDate();
        Order order = readOrder();
        OrderResult orderResult = OrderResult.of(expectedVisitDate, order, eventTypes);
        outputView.printEventBenefitsPreview(EventBenefitsPreviewResponse.from(orderResult));
    }

    private ExpectedVisitDate readExpectedVisitDate() {
        return InputUtil.retryOnException(() -> {
            ExpectedVisitDateRequest dto = inputView.readExpectedVisitDate();
            return dto.toExpectedVisitDate();
        });
    }

    private Order readOrder() {
        return InputUtil.retryOnException(() -> {
            OrderRequest dto = inputView.readOrder();
            return dto.toOrder();
        });
    }
}
