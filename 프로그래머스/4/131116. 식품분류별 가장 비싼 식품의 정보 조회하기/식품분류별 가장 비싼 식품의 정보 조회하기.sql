-- 코드를 입력하세요
-- 그룹화, max 가격,

-- 윈도우 함수

select T.CATEGORY, T.PRICE, T.PRODUCT_NAME
from (
    select CATEGORY, PRICE, PRODUCT_NAME,
    RANK() OVER(PARTITION BY CATEGORY ORDER BY PRICE DESC) as R
    from FOOD_PRODUCT
) T
where T.R = 1 and (T.CATEGORY = '과자' or T.CATEGORY = '국' or T.CATEGORY = '김치' or T.CATEGORY = '식용유')
order by T.PRICE DESC
