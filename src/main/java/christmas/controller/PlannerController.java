package christmas.controller;


import christmas.domain.ExpectedVisitDate;
import christmas.domain.Order;
import christmas.domain.OrderResult;
import christmas.domain.event.EventType;
import christmas.dto.request.ExpectedVisitDateRequest;
import christmas.dto.request.OrderRequest;
import christmas.dto.response.BenefitsResponse;
import christmas.dto.response.GiftMenuResponse;
import christmas.dto.response.OrderResponse;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.util.InputUtil;
import java.util.List;

public class PlannerController {
    private static final List<EventType> EVENT_TYPES = List.of(
            EventType.CHRISTMAS_DDAY_DISCOUNT,
            EventType.WEEKDAY_DISCOUNT,
            EventType.WEEKEND_DISCOUNT,
            EventType.SPECIAL_DISCOUNT,
            EventType.GIFT_EVENT
    );

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

        OrderResult orderResult = new OrderResult(date, order, EVENT_TYPES);

        outputView.printEventBenefitsPreviewMessage();
        outputView.printOrder(OrderResponse.from(order));
        outputView.printTotalPriceBeforeDiscount(order.getTotalPrice());
        outputView.printGiftMenu(GiftMenuResponse.from(orderResult.getGiftMenu()));
        outputView.printBenefits(BenefitsResponse.from(orderResult.getBenefits()));
        outputView.printTotalBenefitPrice(orderResult.getTotalBenefitPrice());
        outputView.printTotalPriceAfterDiscount(orderResult.getTotalPriceAfterDiscount());
        outputView.printBadge(orderResult.getBadge().getName());
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
