package christmas.view;

import christmas.dto.request.ExpectedVisitDateRequest;
import christmas.dto.request.OrderRequest;

public interface InputView {
    ExpectedVisitDateRequest readExpectedVisitDate();

    OrderRequest readOrder();
}
