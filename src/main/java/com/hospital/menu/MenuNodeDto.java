package com.hospital.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * API 응답용 메뉴 트리 노드 DTO.
 * DB 엔티티({@link Menu})를 프론트엔드에 맞게 변환한 형태이며,
 * parentId·sortOrder는 트리 구성 후 응답에서 제외한다.
 */
public class MenuNodeDto {

    /** 메뉴 고유 ID (AUTH_MENU.MENU_ID) */
    private Long id;
    /** 메뉴 코드 (권한·라우팅 식별용) */
    private String code;
    /** 화면에 표시할 메뉴명 */
    private String name;
    /** 프론트 라우트 경로 (예: /dashboard) */
    private String path;
    /** 아이콘 식별자 (프론트 아이콘 컴포넌트와 매핑) */
    private String icon;
    /** 하위 메뉴 목록. leaf 노드는 빈 리스트 */
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
