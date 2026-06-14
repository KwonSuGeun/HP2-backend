-- ============================================================
-- 메뉴명 변경
--   1300: '진료'      → '진료관리' (\C9C4\B8CC\AD00\B9AC)
--   1301: '진료 메인' → '진료'     (\C9C4\B8CC)
-- ============================================================

UPDATE AUTH_MENU
SET NAME       = UNISTR('\C9C4\B8CC\AD00\B9AC'),
    UPDATED_AT = SYSTIMESTAMP
WHERE MENU_ID = 1300;

UPDATE AUTH_MENU
SET NAME       = UNISTR('\C9C4\B8CC'),
    UPDATED_AT = SYSTIMESTAMP
WHERE MENU_ID = 1301;

COMMIT;

SELECT MENU_ID, CODE, NAME FROM AUTH_MENU WHERE MENU_ID IN (1300, 1301) ORDER BY MENU_ID;
