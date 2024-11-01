# java-lotto-precourse

# 기능 목록

## 로또
- [ ] 구입 금액에 맞춰 랜덤으로 로또 번호를 발행시키는 기능
- [ ] 당첨 여부를 확인하는 기능

## 돈계산

- [ ] 수익률을 계산하는 기능
  - [ ] 총 당첨금을 계산하는 기능

## 상수
- [ ] 당첨 기준(일치 개수, 보너스 일치 여부, 당첨금액)
- [ ] 에러 문구
- [ ] 기타 시스템 상수


## 입력
- [ ] 로또 구입 금액을 입력받는 기능
- [ ] 당첨 번호를 입력 받는 기능
- [ ] 보너스 번호를 입력 받는 기능


## 예외처리
- [ ] 로또 구입 금액 예외
  - [ ] 1,000원 단위로 나누어 떨어지지 않을 경우
  - [ ] 양수가 아닌(숫자가 아닌) 입력이 주어질 경우
- [ ] 당첨 번호 입력 예외
  - [ ] 6개가 아닌 숫자가 입력될 경우
  - [ ] 숫자가 아닌 문자가 입력되거나 아무것도 입력되지 않는 경우
  - [ ] 범위를 벗어난 숫자가 입력된 경우
- [ ] 보너스 번호 입력 예외
  - [ ] 당첨 번호 입력 예외 2~3번을 적용


## 출력
- [ ] 발행한 로또 수량 및 번호를 출력하는 기능
- [ ] 당첨 내역을 출력하는 기능
- [ ] 수익률을 출력하는 기능
- [ ] 에러 문구 출력하는 기능

## 학습 목표
- 관련 함수를 묶어 클래스를 만들고, 객체들이 협력하여 하나의 큰 기능을 수행하도록 한다.
- 클래스와 함수에 대한 단위 테스트를 통해 의도한 대로 정확하게 작동하는 영역을 확보한다.
- 2주 차 공통 피드백을 최대한 반영한다.

## 추가된 요구사항 중 눈여겨볼 것
- Java Enum을 적용하여 프로그램을 구현한다.
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.


## 3주차 개인 학습 목표
- 테스트 코드를 좀 더 잘 작성하자
- 변수 선언 등 디테일에 더 신경쓰자
- 디미터 법칙을 좀 더 신경쓰자
- 3주차 미션이 끝나고 코드리뷰를 통해 새로운 지식 배우기
