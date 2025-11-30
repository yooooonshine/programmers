-- 코드를 입력하세요
-- 2022년 10월 16일에 대여중인 자동차는 대여중
-- 아니면 대여 가능
-- SELECT distinct CAR_ID, 
-- CASE
--     WHEN START_DATE <= DATE '2022-10-16' and DATE '2022-10-16' <= END_DATE THEN '대여중'
--     ELSE '대여 가능'
-- END as AVAILABILITY
-- FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
-- ORDER BY CAR_ID desc

with C as (
    select distinct H.CAR_ID
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY H
    where exists (
        select *
        from CAR_RENTAL_COMPANY_RENTAL_HISTORY C
        where H.CAR_ID = C.CAR_ID and C.START_DATE <= DATE '2022-10-16' and DATE '2022-10-16' <= C.END_DATE
    )
)



SELECT distinct CAR_ID, 
CASE
    WHEN CAR_ID in (select CAR_ID from C) THEN '대여중'
    ELSE '대여 가능'
END as AVAILABILITY
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
ORDER BY CAR_ID desc