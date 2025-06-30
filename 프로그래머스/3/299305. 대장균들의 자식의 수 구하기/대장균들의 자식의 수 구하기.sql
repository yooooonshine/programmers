select E1.ID as ID, 
case 
when E2.C is null THEN 0
else E2.C end as CHILD_COUNT
from ECOLI_DATA as E1 left outer join (
    select PARENT_ID, count(*) as C
    from ECOLI_DATA
    group by PARENT_ID) as E2
    on E1.ID = E2.PARENT_ID;
