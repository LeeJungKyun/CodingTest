WITH EmployeeAvgScore AS (
    SELECT
        EMP_NO,
        AVG(SCORE) AS AVG_SCORE
    FROM
        HR_GRADE
    WHERE
        YEAR = 2022
    GROUP BY
        EMP_NO
),
EmployeeGrade AS (
    SELECT
        A.EMP_NO,
        A.EMP_NAME,
        A.SAL,
        (
            CASE
                WHEN B.AVG_SCORE >= 96 THEN 'S'
                WHEN B.AVG_SCORE >= 90 THEN 'A'
                WHEN B.AVG_SCORE >= 80 THEN 'B'
                ELSE 'C'
            END
        ) AS GRADE
    FROM
        HR_EMPLOYEES A
    JOIN
        EmployeeAvgScore B ON A.EMP_NO = B.EMP_NO
)
-- 3. 계산된 등급(GRADE)을 사용하여 성과금(BONUS) 계산 및 최종 조회
SELECT
    EMP_NO,
    EMP_NAME,
    GRADE,
    (
        CASE
            WHEN GRADE = 'S' THEN SAL * 0.2
            WHEN GRADE = 'A' THEN SAL * 0.15
            WHEN GRADE = 'B' THEN SAL * 0.1
            ELSE 0
        END
    ) AS BONUS
FROM
    EmployeeGrade
ORDER BY
    EMP_NO ASC;
