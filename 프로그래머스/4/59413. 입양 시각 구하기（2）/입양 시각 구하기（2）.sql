with recursive TIMETABLE(hour) as
(
    select 0
    union
    select hour + 1
    from TIMETABLE 
    where hour < 23
)

select T.hour, 
(case when A.COUNT is null then 0
else A.COUNT end) as COUNT
from TIMETABLE as T
left join (select HOUR(DATETIME) as HOUR, count(*) as COUNT
from ANIMAL_OUTS as A
group by HOUR(DATETIME)) as A
on T.hour = A.HOUR;
