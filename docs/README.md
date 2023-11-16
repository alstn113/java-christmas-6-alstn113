# 기능 구현 목록

## Domain

- VisitDate (Class)
    - [x] 방문 날짜가 1~31이 아닐 경우 예외 처리
    - [x] int를 받아서 LocalDate로 변환
- Badge (Enum)
    - [x] 이름과 조건을 가짐
    - [x] 총 혜택 금액에 따라 증정
        - 5천 원 이상: 별
        - 1만 원 이상: 트리
        - 2만 원 이상: 산타
- Order
    - Order (Class)
        - [x] 음료만 있을 경우 예외 처리
        - [x] 메뉴 20개 초과일 경우 예외 처리
        - [x] 중복된 메뉴가 있을 경우 예외 처리
    - OrderItem (Class)
        - [x] 메뉴 개수는 1이상 숫자이 아닌 경우 예외 처리
        - [x] 없는 메뉴일 경우 예외 처리
    - Menu (Enum)
        - [x] 이름과 가격을 가지고 있음
        - [x] 메뉴 이름으로 메뉴 찾기
    - Category (Enum)
        - [x] 카테고리가 메뉴 배열을 가지고 있음
        - [x] 메뉴로 카테고리 찾기
- Event
    - EventResult (Class)
        - [x] 할인 금액과 증정 메뉴(Optional<OrderItem>)를 가짐
    - EventStrategy (Abstract Class)
        - [x] 시작과 종료 날짜를 가짐
        - [x] 이벤트가 진행 중이고, 총 가격이 10,000원 이상인지 확인 - isApplicable
        - [x] 특정한 EventResult를 반환하는 추상 메서드 - applyEvent
        - [x] 할인에 해당하면 특정한 EventResult를 반환하는 메서드 applyEventIfApplicable
    - DecemberStrategy (Abstract Class) - (EventStrategy를 상속)
        - [x] 12월 1일 ~ 12월 31일에만 적용되는 EventStrategy
    - WeekdayDiscountStrategy (Class) - (DecemberStrategy를 상속)
        - [x] 평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
        - [x] applyEvent 메서드 구현
        - [x] isApplicable에서 디저트 개수가 0보다 많고, 평일인지 확인로 Override
    - WeekendDiscountStrategy (Class) - (DecemberStrategy를 상속)
        - [x] 주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
        - [x] applyEvent 메서드 구현
        - [x] isApplicable에서 메인 메뉴 개수가 0보다 많고, 주말인지 확인로 Override
    - SpecialDiscountStrategy (Class) - (DecemberStrategy를 상속)
        - [x] 특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
        - [x] applyEvent 메서드 구현
        - [x] isApplicable에서 달력에 별이 있는지 확인로 Override
    - ChristmasDdayDiscountStrategy (Class) - (EventStrategy 상속)
        - [x] 크리스마스 디데이 할인: (이벤트 기간: 2023.12.1 ~ 2023.12.25)
          1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
          총주문 금액에서 해당 금액만큼 할인
          (e.g. 시작일인 12월 1일에 1,000원, 2일에 1,100원, ..., 25일엔 3,400원 할인)
        - [x] applyEvent 메서드 구현
        - [x] isApplicable에서 12월 1일 ~ 12월 25일인지 확인로 Override
    - Event (Enum)
        - [x] 이벤트 이름과 EventStrategy를 가짐
    - EventGroup (Class)
        - [x] Event List를 가짐
- EventBenefits (Class)
    - [x] VisitDate, Order, EventGroup을 생성자로 받는다
    - [x] 증정 메뉴를 반환할 수 있다
    - [x] 혜택 내역을 반환할 수 있다 - (이벤트는 있지만 혜택이 없을 경우 포함하지 않음)
    - [x] 총 혜택 금액을 반환할 수 있다
    - [x] 총 할인 금액을 반환할 수 있다
    - [x] 해당하는 뱃지를 반환할 수 있다

## View

- 모든 에러 메세지는 [ERROR]
- 출력 형식
    - [x] <주문 메뉴>
    - [x] <할인 전 총주문 금액>
    - [x] <증정 메뉴>
    - [x] <혜택 내역>
    - [x] <총혜택 금액>
    - [x] <할인 후 예상 결제 금액>
    - [x] <12월 이벤트 배지>

## Structure

```
christmas
    ├── Application.java
    ├── controller
    │   └── EventPlannerController.java
    ├── domain
    │   ├── Badge.java
    │   ├── EventBenefits.java
    │   ├── VisitDate.java
    │   ├── event
    │   │   ├── Event.java
    │   │   ├── EventGroup.java
    │   │   ├── EventResult.java
    │   │   ├── EventStrategy.java
    │   │   ├── DecemberEventStrategy.java
    │   │   ├── ChristmasDdayDiscountStrategy.java
    │   │   ├── SpecialDiscountStrategy.java
    │   │   ├── WeekdayDiscountStrategy.java
    │   │   └── WeekendDiscountStrategy.java
    │   │   ├── GiftEventStrategy.java
    │   └── order
    │       ├── Category.java
    │       ├── Menu.java
    │       ├── Order.java
    │       └── OrderItem.java
    ├── exception
    │   ├── AppException.java
    │   ├── ErrorMessage.java
    │   └── InvalidInputException.java
    └── view
        ├── InputView.java
        ├── OutputView.java
        ├── console
        │   ├── ConsoleInputView.java
        │   └── ConsoleOutputView.java
        └── util
            └── InputUtil.java
```