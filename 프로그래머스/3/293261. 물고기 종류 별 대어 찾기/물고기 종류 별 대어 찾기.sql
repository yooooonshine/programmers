SELECT C.ID, D.FISH_NAME, C.LENGTH
FROM FISH_NAME_INFO AS D JOIN (SELECT A.ID, A.FISH_TYPE, A.LENGTH
FROM FISH_INFO AS A JOIN (SELECT FISH_TYPE, MAX(LENGTH) as m_l
    FROM FISH_INFO
    GROUP BY FISH_TYPE) as B
ON A.FISH_TYPE = B.FISH_TYPE and A.LENGTH = B.m_l) AS C
ON D.FISH_TYPE = C.FISH_TYPE
ORDER BY ID ASC;





## 종류 별로, 가장 큰 물고기 ID, 이름, 길이
## 물고기 ID 오름차순