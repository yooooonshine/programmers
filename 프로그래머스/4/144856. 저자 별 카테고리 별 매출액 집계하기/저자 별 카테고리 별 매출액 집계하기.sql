# 2022년 1월 판메 데이터
# AUTHOR_ID와 CATEGORY 별로 판매액

# 1. BOOK_NAME과 BOOK_SALES(정렬한 것, 1월, 특정 북 판매량) 조인
# 2. GATEGORY와 AUTHOR_ID로 group by, 판매량 * 가격으로 



WITH BOOK_NAME as (
    select B.BOOK_ID, B.CATEGORY, B.AUTHOR_ID, B.PRICE, A.AUTHOR_NAME 
    from BOOK as B
    join AUTHOR as A
    on B.AUTHOR_ID = A.AUTHOR_ID
), JANUARY as (
    select BOOK_ID, sum(SALES) as TOTAL_SALES 
    from BOOK_SALES
    where MONTH(SALES_DATE) = 1 && YEAR(SALES_DATE) = 2022
    group by BOOK_ID
)

select D.AUTHOR_ID, D.AUTHOR_NAME, D.CATEGORY, sum(D.TOTAL_SALES) as TOTAL_SALES
from
(select B.AUTHOR_ID, B.AUTHOR_NAME, B.CATEGORY, B.PRICE * J.TOTAL_SALES as TOTAL_SALES
from BOOK_NAME as B
join JANUARY as J
on B.BOOK_ID = J.BOOK_ID) as D
group by D.AUTHOR_ID, D.CATEGORY
order by D.AUTHOR_ID asc, D.CATEGORY desc;

