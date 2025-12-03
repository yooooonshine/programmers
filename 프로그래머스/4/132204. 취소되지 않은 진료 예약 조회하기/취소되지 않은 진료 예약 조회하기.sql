-- 코드를 입력하세요

-- 2022년 4월 13일
-- 취소되지 않은 CS 진료 예약 내역 조회
-- 진료예약번호, 환자이름, 환자번호, 진료과코드, 의사이름, 진료예약일시 항목
-- 진료예약일시 오름차순

SELECT A.APNT_NO, P.PT_NAME, P.PT_NO,A.MCDP_CD, D.DR_NAME, A.APNT_YMD
from APPOINTMENT A
join PATIENT P
on A.PT_NO = P.PT_NO
join DOCTOR D
on A.MDDR_ID = D.DR_ID
where TRUNC(A.APNT_YMD) = DATE '2022-04-13' and APNT_CNCL_YN = 'N'
order by APNT_YMD