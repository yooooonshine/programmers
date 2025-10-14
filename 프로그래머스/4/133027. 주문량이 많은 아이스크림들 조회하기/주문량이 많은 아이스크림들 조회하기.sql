-- 코드를 입력하세요

## 7월에는 shiment가 다를 수 있어, shipment가 기본키

with DIST as (
    select FLAVOR, sum(TOTAL_ORDER) as TOTAL_ORDER
    from JULY
    group by FLAVOR
)


# SELECT T.FLAVOR
# from (
#     FIRST_HALF
#     union
#     JULY
# ) as T;

select F.FLAVOR
from FIRST_HALF as F
join DIST as D
on F.FLAVOR = D.FLAVOR
order by F.TOTAL_ORDER + D.TOTAL_ORDER desc
limit 3;