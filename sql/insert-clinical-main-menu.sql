-- ============================================================
-- "진료"(MENU_ID=1300) 그룹에 자식이 생기면서 /clinical 직접 이동이
-- 불가능해졌으므로, /clinical로 이동하는 자식 메뉴 "진료 메인" 추가
-- (청구 메인, 스탭 메인과 동일한 네이밍 패턴)
--
-- NAME은 클라이언트 NLS_LANG 인코딩과 무관하게 동작하도록
-- UNISTR 유니코드 리터럴 사용: '진료 메인' = \C9C4\B8CC \BA54\C778
-- ============================================================

INSERT INTO AUTH_MENU (MENU_ID, PARENT_ID, CODE, NAME, PATH, ICON, SORT_ORDER, IS_ACTIVE)
VALUES (1301, 1300, 'CLINICAL_MAIN', UNISTR('\C9C4\B8CC \BA54\C778'), '/clinical', NULL, 1, 'Y');

COMMIT;

-- 변경 결과 확인
SELECT MENU_ID, PARENT_ID, CODE, NAME, PATH, SORT_ORDER
FROM AUTH_MENU
WHERE MENU_ID IN (1300, 1301, 1400)
ORDER BY MENU_ID;
