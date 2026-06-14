-- ============================================================
-- "진료 지원"(MENU_ID=1400)을 "진료"(MENU_ID=1300)의 자식으로 이동
-- 하위 메뉴(1401, 1404, 1405, 1406)는 PARENT_ID=1400 참조를 유지하므로
-- 자동으로 3depth(손자) 노드가 됨
-- ============================================================

UPDATE AUTH_MENU
SET PARENT_ID  = 1300,
    UPDATED_AT = SYSTIMESTAMP
WHERE MENU_ID = 1400;

COMMIT;

-- 변경 결과 확인
SELECT MENU_ID, PARENT_ID, CODE, NAME, PATH, SORT_ORDER
FROM AUTH_MENU
WHERE MENU_ID IN (1300, 1400, 1401, 1404, 1405, 1406)
ORDER BY MENU_ID;
