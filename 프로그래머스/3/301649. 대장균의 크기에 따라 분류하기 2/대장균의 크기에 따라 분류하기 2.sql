SELECT
    ID,
    CASE
        WHEN T.colony_quartile = 1 THEN 'CRITICAL'
        WHEN T.colony_quartile = 2 THEN 'HIGH'
        WHEN T.colony_quartile = 3 THEN 'MEDIUM'
        WHEN T.colony_quartile = 4 THEN 'LOW'
        ELSE NULL
    END AS COLONY_NAME
FROM (
    SELECT
        *,
        NTILE(4) OVER (ORDER BY SIZE_OF_COLONY DESC) AS colony_quartile
    FROM ECOLI_DATA
) T
ORDER BY ID ASC;