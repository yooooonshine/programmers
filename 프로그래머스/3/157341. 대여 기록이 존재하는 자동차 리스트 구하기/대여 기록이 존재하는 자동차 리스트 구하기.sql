-- 코드를 입력하세요
-- 자동차 종류가 세단
-- 10월에 대여 시작한 자동차 ID 리스트
-- ID 리스트 중복 x
-- 자동차 ID 기준 내림차순
SELECT distinct C.CAR_ID
FROM CAR_RENTAL_COMPANY_CAR C
join CAR_RENTAL_COMPANY_RENTAL_HISTORY H
on C.CAR_ID = H.CAR_ID
where CAR_TYPE = '세단' and EXTRACT(MONTH FROM START_DATE) = 10
order by C.CAR_ID desc
