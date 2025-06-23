select count(*) as FISH_COUNT, MONTH(TIME) as MONTH
from FISH_INFO
group by MONTH(TIME)
order by MONTH asc;

## 월 별 물고기 수
## 월 오름차순