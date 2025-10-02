# 들어올 당시 중성화 x
# 나갈 때 중성화된
# 아이디, 생물주오, 이름
# 아이디순 아이디순

# intact male // 중성화 x
# intact female // 중성화 x
# neutered male
# spayed female

select I.ANIMAL_ID, I.ANIMAL_TYPE, I.NAME
from ANIMAL_INS as I
join ANIMAL_OUTS as O
on I.ANIMAL_ID = O.ANIMAL_ID
where (I.SEX_UPON_INTAKE = 'Intact Male' && O.SEX_UPON_OUTCOME = 'Neutered Male') || (I.SEX_UPON_INTAKE = 'Intact Female' && O.SEX_UPON_OUTCOME = 'Spayed Female')
order by I.ANIMAL_ID;