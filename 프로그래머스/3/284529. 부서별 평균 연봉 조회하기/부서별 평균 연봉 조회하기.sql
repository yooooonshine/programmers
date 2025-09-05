select hr.DEPT_ID as DEPT_ID, hr.DEPT_NAME_EN as DEPT_NAME_EN, 
ROUND(AVG(em.SAL)) as AVG_SAL

from HR_DEPARTMENT as hr
join HR_EMPLOYEES as em
on hr.DEPT_ID = em.DEPT_ID
group by hr.DEPT_ID
order by AVG_SAL desc;