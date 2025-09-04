# select count(*) from ECOLI_DATA;


# select ID, 
# when
# case
# else 
# end

select t.ID as ID,
case 
when V > (select count(*) from ECOLI_DATA) / 4 * 3 then 'CRITICAL'
when V > (select count(*) from ECOLI_DATA) / 4 * 2 then 'HIGH'
when V > (select count(*) from ECOLI_DATA) / 4 * 1 then 'MEDIUM'
else 'LOW'
end as COLONY_NAME

from (select ID, ROW_NUMBER() OVER(ORDER BY SIZE_OF_COLONY) as V from ECOLI_DATA) as t
order by t.ID asc;
