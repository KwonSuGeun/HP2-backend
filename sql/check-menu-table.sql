-- HOSPITAL.AUTH_MENU 테이블 확인용 (SQLGate)

SELECT column_name, data_type, nullable
FROM all_tab_columns
WHERE owner = 'HOSPITAL'
  AND table_name = 'AUTH_MENU'
ORDER BY column_id;

SELECT *
FROM HOSPITAL.AUTH_MENU
WHERE IS_ACTIVE = 'Y'
ORDER BY SORT_ORDER;
