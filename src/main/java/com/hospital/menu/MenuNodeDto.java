package com.hospital.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * GET /api/menus JSON 응답 1노드 — 프론트 SidebarItem 과 필드 1:1 대응.
 *
 * <p>Menu 대비 parentId, sortOrder 없음 (트리 구성 후 제거).
 *
 * <p>[JSON 필드]
 * <ul>
 *   <li>id, code, name, path, icon</li>
 *   <li>children — List&lt;MenuNodeDto&gt; 재귀. leaf 는 빈 배열 []</li>
 * </ul>
 *
 * <p>[JSON 예 — leaf]
 * <pre>
 * { "id":3, "code":"PATIENT_LIST", "name":"환자 목록",
 *   "path":"/patients", "icon":null, "children":[] }
 * </pre>
 *
 * <p>[JSON 예 — 그룹(자식 있음)]
 * <pre>
 * { "id":2, "code":"PATIENT", "name":"환자 관리", "path":null,
 *   "icon":"People", "children":[ { ... } ] }
 * </pre>
 */
public class MenuNodeDto {

    private Long id;
    private String code;
    private String name;
    private String path;
    private String icon;
    /** 하위 메뉴. buildTree() 재귀 결과. leaf 는 new ArrayList&lt;&gt;() */
    private List<MenuNodeDto> children = new ArrayList<>();

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

    public List<MenuNodeDto> getChildren() {
        return children;
    }

    public void setChildren(List<MenuNodeDto> children) {
        this.children = children;
    }
}
