# hospital-backend

Spring Boot + MyBatis + Oracle API 서버 (프론트와 **별도 프로젝트**)

## 역할

```
Next.js (hospital-project)  →  HTTP  →  이 프로젝트 (:8081)  →  Oracle CMH.AUTH_MENU
```

프론트는 DB를 모름. **이 프로젝트만** DB에 접속합니다.

## 실행

```bash
cd hospital-backend
mvn spring-boot:run
```

- API: http://localhost:8081/api/menus

## DB 설정

`src/main/resources/application.yml`

```yaml
url: jdbc:oracle:thin:@localhost:1521:XE
username: hospital
password: "1111"
```

## MyBatis SQL

| 파일 | 내용 |
|------|------|
| `src/main/resources/mapper/MenuMapper.xml` | `CMH.AUTH_MENU` SELECT |
| `sql/check-menu-table.sql` | SQLGate 확인용 |

## IntelliJ

**File → Open** → `hospital-backend` 폴더만 열어서 백엔드 작업  
프론트(`hospital-project`)와 **창을 따로** 두면 편합니다.

## 프론트 연동

프론트 `.env.local`:

```
NEXT_PUBLIC_API_URL=http://localhost:8081
```

백엔드 먼저 켜고 → 프론트 `npm run dev`
