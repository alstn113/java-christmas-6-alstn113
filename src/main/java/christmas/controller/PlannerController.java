package christmas.controller;


import christmas.domain.ExpectedVisitDate;
import christmas.domain.Order;
import christmas.dto.request.ExpectedVisitDateRequest;
import christmas.dto.request.OrderRequest;
import christmas.dto.response.OrderResponse;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.util.InputUtil;

public class PlannerController {
    private final InputView inputView;
    private final OutputView outputView;

    public PlannerController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWelcomeMessage();
        ExpectedVisitDate date = readExpectedVisitDate();
        Order order = readOrder();

        outputView.printOrder(OrderResponse.from(order));
        outputView.printTotalPriceBeforeDiscount(order.getTotalPrice());
        outputView.printEventBenefitsPreviewMessage();
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
