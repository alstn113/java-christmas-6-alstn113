package christmas.view.console;

import christmas.view.OutputView;

public class ConsoleOutputView implements OutputView {
    @Override
    public void displayWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    @Override
    public void displayEventPreviewMessage() {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    @Override
    public void displayOrderedMenu() {
        System.out.println();
        System.out.println("<주문 메뉴>");
    }

    @Override
    public void displayTotalPriceBeforeDiscount() {
        System.out.println();
        System.out.println("<할인 전 총주문 금액>");
    }

    @Override
    public void displayGiftMenu() {
        System.out.println();
        System.out.println("<증정 메뉴>");
    }

    @Override
    public void displayBenefitDetails() {
        System.out.println();
        System.out.println("<혜택 내역>");
    }

    @Override
    public void displayTotalBenefitAmount() {
        System.out.println();
        System.out.println("<총혜택 금액>");
    }

    @Override
    public void displayTotalPriceAfterDiscount() {
        System.out.println();
        System.out.println("<할인 후 예상 결제 금액>");
    }

    @Override
    public void displayDecemberEventBadge() {
        System.out.println();
        System.out.println("<12월 이벤트 배지>");
    }
}