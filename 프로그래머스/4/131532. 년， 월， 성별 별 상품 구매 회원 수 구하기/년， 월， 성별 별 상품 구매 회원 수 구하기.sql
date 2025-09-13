# GENDER는 비어있을 수 있다.
# 년, 월, 성별 별로 상품 구매한 회원수 집계
# year(), month(), 

# online sale을 year, month, uesrid로 분류
# userid로 join하기, 
# year, month, gender로 group by

select O.YEAR as YEAR, O.MONTH as MONTH, U.GENDER as GENDER, count(distinct U.USER_ID) as USERS
from (select * from USER_INFO where GENDER is not null) as U
join (select YEAR(SALES_DATE) as YEAR, MONTH(SALES_DATE) as MONTH, USER_ID
from ONLINE_SALE) as O
on U.USER_ID = O.USER_ID
group by O.YEAR, O.MONTH, U.GENDER
order by YEAR, MONTH, GENDER;