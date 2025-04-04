SELECT A.DEPT_ID, A.DEPT_NAME_EN, ROUND(B.AVG_SAL,0) AS AVG_SAL
FROM HR_DEPARTMENT A JOIN (
    SELECT DEPT_ID, AVG(SAL) AS AVG_SAL
    FROM HR_EMPLOYEES
    GROUP BY DEPT_ID
) B
ON A.DEPT_ID = B.DEPT_ID
ORDER BY AVG_SAL DESC;