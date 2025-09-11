-- 2번형질X, 1번 또는 3번 형질을 갖고 있는 대장균 개체의 수를 출력

SELECT COUNT(*) AS COUNT
FROM ECOLI_DATA
WHERE (GENOTYPE & 2) = 0
AND ((GENOTYPE & 1)  != 0 OR (GENOTYPE & 4) != 0);