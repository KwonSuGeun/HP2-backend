-- ============================================================
-- HIS 테이블 생성 스크립트 (Oracle XE)
-- 원본: 192.168.1.128:1521/XE - HOSPITAL 스키마에서 추출 (2026-06-11)
-- 실행 순서: 부모 테이블(STAFF_DEPARTMENT, AUTH_MENU) → 자식 테이블(STAFF)
-- ============================================================

-- ------------------------------------------------------------
-- 1. 부서 (STAFF_DEPARTMENT)
-- ------------------------------------------------------------
CREATE TABLE STAFF_DEPARTMENT (
    DEPARTMENT_ID       VARCHAR2(20)    NOT NULL,
    DEPARTMENT_NAME     VARCHAR2(100)   NOT NULL,
    IS_ACTIVE           CHAR(1)         DEFAULT 'Y' NOT NULL,
    STAFF_EXTENSION_NO  VARCHAR2(20),
    CONSTRAINT PK_STAFF_DEPARTMENT      PRIMARY KEY (DEPARTMENT_ID),
    CONSTRAINT CK_STAFF_DEPARTMENT_ACTIVE CHECK (IS_ACTIVE IN ('Y', 'N'))
);

-- ------------------------------------------------------------
-- 2. 직원 (STAFF)
-- ------------------------------------------------------------
CREATE TABLE STAFF (
    STAFF_ID            VARCHAR2(20)    NOT NULL,
    STAFF_PASSWORD      VARCHAR2(255)   NOT NULL,
    STAFF_NAME          VARCHAR2(50)    NOT NULL,
    STAFF_TYPE          VARCHAR2(10)    NOT NULL,
    STAFF_ROLE_CODE     VARCHAR2(20)    NOT NULL,
    STAFF_DEPARTMENT_ID VARCHAR2(20)    NOT NULL,
    STAFF_RANK_CODE     VARCHAR2(10)    NOT NULL,
    STAFF_POSITION_CODE VARCHAR2(10),
    STAFF_PHONE         VARCHAR2(20)    NOT NULL,
    STAFF_EXTENSION_NO  VARCHAR2(20),
    STAFF_EMAIL         VARCHAR2(100),
    STAFF_HIRE_DATE     DATE            NOT NULL,
    STAFF_STATUS        VARCHAR2(10)    NOT NULL,
    STAFF_BIRTH_DATE    DATE            NOT NULL,
    STAFF_LICENSE_NO    VARCHAR2(50),
    STAFF_ADDRESS       VARCHAR2(200),
    CONSTRAINT PK_STAFF             PRIMARY KEY (STAFF_ID),
    CONSTRAINT CK_STAFF_TYPE        CHECK (STAFF_TYPE IN ('DOC', 'NUR', 'ADM')),
    CONSTRAINT CK_STAFF_STATUS      CHECK (STAFF_STATUS IN ('재직', '휴직', '퇴직')),
    CONSTRAINT FK_STAFF_DEPARTMENT  FOREIGN KEY (STAFF_DEPARTMENT_ID)
        REFERENCES STAFF_DEPARTMENT (DEPARTMENT_ID)
);

-- ------------------------------------------------------------
-- 3. 권한 메뉴 (AUTH_MENU) - 자기참조 (PARENT_ID → MENU_ID)
-- ------------------------------------------------------------
CREATE TABLE AUTH_MENU (
    MENU_ID     NUMBER          NOT NULL,
    PARENT_ID   NUMBER,
    CODE        VARCHAR2(50)    NOT NULL,
    NAME        VARCHAR2(100)   NOT NULL,
    PATH        VARCHAR2(200),
    ICON        VARCHAR2(50),
    SORT_ORDER  NUMBER,
    IS_ACTIVE   CHAR(1)         DEFAULT 'Y' NOT NULL,
    CREATED_AT  TIMESTAMP(6)    DEFAULT SYSTIMESTAMP NOT NULL,
    UPDATED_AT  TIMESTAMP(6)    DEFAULT SYSTIMESTAMP NOT NULL,
    CONSTRAINT PK_AUTH_MENU         PRIMARY KEY (MENU_ID),
    CONSTRAINT CK_AUTH_MENU_IS_ACTIVE CHECK (IS_ACTIVE IN ('Y', 'N')),
    CONSTRAINT FK_AUTH_MENU_PARENT  FOREIGN KEY (PARENT_ID)
        REFERENCES AUTH_MENU (MENU_ID)
);

-- ------------------------------------------------------------
-- 4. 시퀀스
-- ------------------------------------------------------------
CREATE SEQUENCE SEQ_AUTH_MENU
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NOCACHE
    NOCYCLE;
