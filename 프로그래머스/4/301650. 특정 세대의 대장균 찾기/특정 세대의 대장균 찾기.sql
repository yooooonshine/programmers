## null인 것에 대한 id1
## id1을 포함한 id2
## id2를 포함한 id3

select ID 
from ECOLI_DATA
where PARENT_ID in 
(select ID
from ECOLI_DATA
where PARENT_ID in
(select ID
from ECOLI_DATA
where PARENT_ID is null))
order by ID asc;
