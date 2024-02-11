package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest2 extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 적용된_이벤트가_없는_경우() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains(
                    "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다." + LINE_SEPARATOR,
                    "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)" + LINE_SEPARATOR,
                    "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)" + LINE_SEPARATOR,
                    "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!" + LINE_SEPARATOR + LINE_SEPARATOR,
                    "<주문 메뉴>" + LINE_SEPARATOR,
                    "타파스 1개" + LINE_SEPARATOR,
                    "제로콜라 1개" + LINE_SEPARATOR + LINE_SEPARATOR,
                    "<할인 전 총주문 금액>" + LINE_SEPARATOR,
                    "8,500원" + LINE_SEPARATOR + LINE_SEPARATOR,
                    "<증정 메뉴>" + LINE_SEPARATOR,
                    "없음" + LINE_SEPARATOR + LINE_SEPARATOR,
                    "<혜택 내역>" + LINE_SEPARATOR,
                    "없음" + LINE_SEPARATOR + LINE_SEPARATOR,
                    "<총혜택 금액>" + LINE_SEPARATOR,
                    "0원" + LINE_SEPARATOR + LINE_SEPARATOR,
                    "<할인 후 예상 결제 금액>" + LINE_SEPARATOR,
                    "8,500원" + LINE_SEPARATOR + LINE_SEPARATOR,
                    "<12월 이벤트 배지>" + LINE_SEPARATOR,
                    "없음"
            );
        });
    }

    @Test
    void 기대하는_이벤트_플래너의_예시_모습() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다." + LINE_SEPARATOR,
                    "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)" + LINE_SEPARATOR,
                    "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)" + LINE_SEPARATOR,
                    "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!" + LINE_SEPARATOR + LINE_SEPARATOR,
                    "<주문 메뉴>" + LINE_SEPARATOR,
                    "티본스테이크 1개" + LINE_SEPARATOR,
                    "바비큐립 1개" + LINE_SEPARATOR,
                    "초코케이크 2개" + LINE_SEPARATOR,
                    "제로콜라 1개" + LINE_SEPARATOR + LINE_SEPARATOR,
                    "<할인 전 총주문 금액>" + LINE_SEPARATOR,
                    "142,000원" + LINE_SEPARATOR + LINE_SEPARATOR,
                    "<증정 메뉴>" + LINE_SEPARATOR,
                    "샴페인 1개" + LINE_SEPARATOR + LINE_SEPARATOR,
                    "<혜택 내역>" + LINE_SEPARATOR,
                    "크리스마스 디데이 할인: -1,200원" + LINE_SEPARATOR,
                    "평일 할인: -4,046원" + LINE_SEPARATOR,
                    "특별 할인: -1,000원" + LINE_SEPARATOR,
                    "증정 이벤트: -25,000원" + LINE_SEPARATOR + LINE_SEPARATOR,
                    "<총혜택 금액>" + LINE_SEPARATOR,
                    "-31,246원" + LINE_SEPARATOR + LINE_SEPARATOR,
                    "<할인 후 예상 결제 금액>" + LINE_SEPARATOR,
                    "135,754원" + LINE_SEPARATOR + LINE_SEPARATOR,
                    "<12월 이벤트 배지>" + LINE_SEPARATOR,
                    "산타"
            );
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
