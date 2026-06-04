package com.hospital.menu;

/**
 * CMH.AUTH_MENU 테이블 ↔ MyBatis resultType 매핑 엔티티.
 *
 * <p>DB 조회 직후·buildTree() 입력 단계에서 사용.
 * API JSON 으로는 MenuNodeDto 가 내려가며, parentId/sortOrder 는 응답에서 제외됨.
 *
 * <p>[DB 컬럼 ↔ 필드]
 * <ul>
 *   <li>MENU_ID    → id</li>
 *   <li>PARENT_ID  → parentId (null = 최상위)</li>
 *   <li>CODE, NAME, PATH, ICON</li>
 *   <li>SORT_ORDER → sortOrder (형제 간 정렬, buildTree 시 사용)</li>
 * </ul>
 *
 * <p>[평면 row 예]
 * id=3, parentId=2, name="환자 목록", path="/patients"
 * → buildTree 후 MenuNodeDto(id=3) 가 MenuNodeDto(id=2).children[] 안에 들어감
 */
public class Menu {

    /** MENU_ID — 프론트 SidebarItem.id 와 동일 */
    private Long id;
    private String code;
    private String name;
    /** 프론트 Link href. 그룹 메뉴는 null 일 수 있음 */
    private String path;
    /** 프론트 SidebarIcons 맵 키 문자열 */
    private String icon;
    /** PARENT_ID. null 이면 루트. buildTree 필터 조건 */
    private Long parentId;
    /** SORT_ORDER. buildTree 시 형제 노드 정렬용 */
    private Integer sortOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}
