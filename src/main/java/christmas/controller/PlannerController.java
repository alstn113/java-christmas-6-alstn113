package christmas.controller;


import christmas.domain.ExpectedVisitDate;
import christmas.dto.request.ExpectedVisitDateRequest;
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
        ExpectedVisitDate date = readExpectedVisitDate();
        System.out.println(date.getDate());
    }

    private ExpectedVisitDate readExpectedVisitDate() {
        return InputUtil.retryOnException(() -> {
            ExpectedVisitDateRequest dto = inputView.readExpectedVisitDate();
            return dto.toExpectedVisitDate();
        });
    }
}
