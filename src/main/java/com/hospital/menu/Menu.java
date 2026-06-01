package com.hospital.menu;

/**
 * CMH.AUTH_MENU 테이블 매핑 엔티티.
 * MyBatis가 {@link com.hospital.menu.MenuMapper} 쿼리 결과를 이 타입으로 변환한다.
 */
public class Menu {

    /** MENU_ID */
    private Long id;
    /** 메뉴 코드 */
    private String code;
    /** 메뉴명 */
    private String name;
    /** 프론트 라우트 경로 */
    private String path;
    /** 아이콘 식별자 */
    private String icon;
    /** PARENT_ID. null이면 최상위 메뉴 */
    private Long parentId;
    /** SORT_ORDER. 형제 메뉴 간 정렬 순서 */
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
