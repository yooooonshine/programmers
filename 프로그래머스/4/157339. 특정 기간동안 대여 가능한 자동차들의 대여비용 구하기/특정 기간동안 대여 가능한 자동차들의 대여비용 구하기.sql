-- 코드를 입력하세요



# 자동차가 세단 or SUV
# 2022년 11월 1일부터 2022년 11월 30일까지 대여 가능
# 30일 간의 대여 금액이 50이상 22이하

with DISCOUNT30 as (
    select *
    from CAR_RENTAL_COMPANY_DISCOUNT_PLAN 
    where DURATION_TYPE = '30일 이상'
), HISTORY2022 as (
    select distinct T.CAR_ID
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY as T
    where !(T.CAR_ID = any(
        select T2.CAR_ID
        from CAR_RENTAL_COMPANY_RENTAL_HISTORY as T2
        where !(T2.START_DATE > DATE('2022-11-30') or T2.END_DATE < DATE('2022-11-1')))
    )
)

select distinct R.CAR_ID, R.CAR_TYPE, CONVERT((R.DAILY_FEE * 30) * (100 - discount_rate) / 100, SIGNED) as FEE
from (
    select C.CAR_ID, C.CAR_TYPE, C.DAILY_FEE
    from CAR_RENTAL_COMPANY_CAR as C
    left join HISTORY2022 as H
    on C.CAR_ID = H.CAR_ID
    where H.CAR_ID is not null
) as R
join DISCOUNT30 as D
on R.CAR_TYPE = D.CAR_TYPE
where (R.CAR_TYPE = '세단' or R.CAR_TYPE = 'SUV') and (CONVERT((R.DAILY_FEE * 30) * (100 - discount_rate) / 100, SIGNED) >= 500000 and CONVERT((R.DAILY_FEE * 30) * (100 - discount_rate) / 100, SIGNED) < 2000000)
order by FEE desc, CAR_TYPE asc, CAR_ID desc

