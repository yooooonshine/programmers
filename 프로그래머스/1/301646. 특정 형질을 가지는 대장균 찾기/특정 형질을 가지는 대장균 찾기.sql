select count(*) as COUNT
from ECOLI_DATA
where BIN(GENOTYPE) NOT LIKE "%1_" && (BIN(GENOTYPE) LIKE "%1" || BIN(GENOTYPE) LIKE "%1__");

# 분ㄹ화 시작 개체 -> 부모 개체
# 나온 개체 -> 자식 개체

# 2번 형질을 보유하지 않으면서 1번이나 3번 형질 보유하는 대장균 수