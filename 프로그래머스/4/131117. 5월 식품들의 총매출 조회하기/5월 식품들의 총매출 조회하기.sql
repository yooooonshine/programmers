# 생산일자가 22년 5월 식품, 식품ID,식품 이름, 총매출
# AMOUNT *  PRICE

# 총매출 내림차순, 식품ID 오름차순

with MAY as (
    select PRODUCT_ID, AMOUNT
    from FOOD_ORDER
    where YEAR(PRODUCE_DATE) = 2022 && MONTH(PRODUCE_DATE) = 5
)



select P.PRODUCT_ID, P.PRODUCT_NAME, sum(M.AMOUNT) * P.PRICE as TOTAL_SALES
from FOOD_PRODUCT as P
join MAY as M
on P.PRODUCT_ID = M.PRODUCT_ID
group by P.PRODUCT_ID
order by  TOTAL_SALES desc, PRODUCT_ID asc;
