-- 코드를 입력하세요
-- 종류가 세단 ,SUV 인 자동차 중
-- 2022년 11월 1일부터 2022년 11월 30일까지 대여 가능하고
-- 30일간의 대여 금액이 50만원 이상 200만원 미만인 자동차

-- 대여기록은 CAR_ID가 중복된다.해당 날에 대여 기록이 있는 것을 모두 찾는다.

-- 정렬
WITH SESUV AS (
    select *
    from CAR_RENTAL_COMPANY_CAR
    where CAR_TYPE = 'SUV' or CAR_TYPE = '세단'
), CANTRENT AS (
    select distinct CAR_ID
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where not (END_DATE < DATE '2022-11-01' or DATE '2022-11-30' < START_DATE)
), CANRENT AS (
    select distinct CAR_ID
    from CAR_RENTAL_COMPANY_CAR
    where CAR_ID not in (select CAR_ID from CANTRENT)
), THIRTY AS (
    select CAR_TYPE, DISCOUNT_RATE
    from CAR_RENTAL_COMPANY_DISCOUNT_PLAN
    where DURATION_TYPE = '30일 이상'
)


select C.CAR_ID, C.CAR_TYPE, 30 * C.DAILY_FEE * (100 - T.DISCOUNT_RATE) / 100 as FEE
from CAR_RENTAL_COMPANY_CAR C
join THIRTY T
on C.CAR_TYPE = T.CAR_TYPE
where C.CAR_ID in (select CAR_ID from CANRENT) and 30 * C.DAILY_FEE * (100 - T.DISCOUNT_RATE) / 100 >= 500000 and 30 * C.DAILY_FEE * (100 - T.DISCOUNT_RATE) / 100 < 2000000
order by FEE desc, CAR_TYPE asc, CAR_ID desc


-- select * from CAR_RENTAL_COMPANY_RENTAL_HISTORY order by CAR_ID

