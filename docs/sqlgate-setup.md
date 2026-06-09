# SQLGate 접속 설정 (팀원용)

`hospital-backend` clone 후 Oracle DB에 SQLGate로 접속하는 방법입니다.

백엔드 DB 설정은 `src/main/resources/application.yml` 과 동일하게 맞추면 됩니다.

---

## 사전 준비

1. **Oracle XE** 설치 및 실행 (로컬 개발 기준)
2. **SQLGate for Oracle Developer** 설치  
   - [SQLGate 다운로드](https://www.sqlgate.com/)
3. `hospital` 스키마·계정 및 `HOSPITAL.AUTH_MENU` 테이블 준비

---

## 연결 추가 (개발용 — 추천)

SQLGate 실행 → **새 연결** (또는 연결 목록에서 `+`)

| 항목 | 값 |
|------|-----|
| 연결 유형 | 직접 연결 |
| Host | `localhost` |
| Port | `1521` |
| Service Name | `XE` |
| User | `hospital` |
| Password | `1111` |
| 문자셋 | `AL32UTF8` |
| 유니코드 | 사용 |

입력 후 **연결 테스트** → 성공하면 **저장**.

별칭(Alias) 예시: `hospital@localhost:1521:XE`

---

## 접속 확인

연결 후 아래 SQL을 실행해 메뉴 테이블이 보이는지 확인합니다.

파일: `sql/check-menu-table.sql`

```sql
SELECT *
FROM HOSPITAL.AUTH_MENU
WHERE IS_ACTIVE = 'Y'
ORDER BY SORT_ORDER;
```

행이 조회되면 DB·계정 설정이 정상입니다.

---

## 백엔드와 맞춰 보기

| 구분 | SQLGate | Spring Boot (`application.yml`) |
|------|---------|----------------------------------|
| Host | `localhost` | `jdbc:oracle:thin:@localhost:1521:XE` |
| Port | `1521` | (URL에 포함) |
| DB | `XE` | (URL에 포함) |
| User | `hospital` | `hospital` |
| Password | `1111` | `1111` |

SQLGate에서 접속되면 백엔드도 같은 조건으로 동작해야 합니다.

```bash
cd hospital-backend
mvn spring-boot:run
```

- API: http://localhost:8081/api/menus

---

## 자주 나는 문제

### ORA-12541 / 리스너 없음

- Oracle 서비스(리스너)가 실행 중인지 확인
- Windows: 서비스에서 `OracleServiceXE`, `OracleXETNSListener` 시작

### ORA-01017 / 계정·비밀번호 오류

- User / Password가 `application.yml` 과 같은지 확인
- 계정 잠금 시 DBA에게 문의

### `localhost`인데 팀원 PC에서 안 됨

- `localhost`는 **각자 PC**를 가리킵니다.
- 팀원 PC에도 Oracle XE가 설치·실행되어 있어야 합니다.
- 공용 DB 서버를 쓰는 경우 Host를 서버 IP로 변경 (예: `192.168.x.x`)

---

## Git 관련 (팀 공유)

| 방법 | 권장 |
|------|------|
| 이 문서(`docs/sqlgate-setup.md`) 따라 수동 입력 | ✅ 권장 |
| `application.yml` 의 접속 정보 참고 | ✅ 권장 |
| `SQLData.dat` 파일을 Git에 올리기 | ❌ 비권장 (바이너리, 비밀번호·PC 종속) |

비밀번호는 추후 `application-local.yml` 등 로컬 전용 파일로 분리할 수 있습니다.  
(이미 `.gitignore`에 `application-local.yml` 등록됨)

---

## 참고

- DB/API 흐름: [DB_FLOW.md](./DB_FLOW.md)
- 프로젝트 개요: [README.md](../README.md)
