-- ============================================================
-- "진료"(MENU_ID=1301) 하위 자식 메뉴 3개 추가
--   1302: 외래 진료 (/clinical)              \C678\B798 \C9C4\B8CC
--   1303: 진료 기록 (/clinical/record)       \C9C4\B8CC \AE30\B85D
--   1304: 처방 관리 (/clinical/prescription) \CC98\BC29 \AD00\B9AC
-- NAME은 클라이언트 인코딩과 무관하게 UNISTR 유니코드 리터럴 사용
-- ============================================================

INSERT INTO AUTH_MENU (MENU_ID, PARENT_ID, CODE, NAME, PATH, ICON, SORT_ORDER, IS_ACTIVE)
VALUES (1302, 1301, 'CLINICAL_OUTPATIENT', UNISTR('\C678\B798 \C9C4\B8CC'), '/clinical', NULL, 1, 'Y');

INSERT INTO AUTH_MENU (MENU_ID, PARENT_ID, CODE, NAME, PATH, ICON, SORT_ORDER, IS_ACTIVE)
VALUES (1303, 1301, 'CLINICAL_RECORD', UNISTR('\C9C4\B8CC \AE30\B85D'), '/clinical/record', NULL, 2, 'Y');

INSERT INTO AUTH_MENU (MENU_ID, PARENT_ID, CODE, NAME, PATH, ICON, SORT_ORDER, IS_ACTIVE)
VALUES (1304, 1301, 'CLINICAL_PRESCRIPTION', UNISTR('\CC98\BC29 \AD00\B9AC'), '/clinical/prescription', NULL, 3, 'Y');

COMMIT;

SELECT MENU_ID, PARENT_ID, CODE, NAME, PATH, SORT_ORDER
FROM AUTH_MENU
WHERE MENU_ID IN (1301, 1302, 1303, 1304)
ORDER BY MENU_ID;
