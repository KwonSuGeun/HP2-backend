package com.hospital.menu;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DB 평면 메뉴 목록 → API용 계층 트리 변환 (Map 2-pass, 재귀 없음).
 *
 * <p>[데이터 변환 흐름]
 * <ol>
 *   <li>menuMapper.selectAllMenus() → List&lt;Menu&gt; (평면, parentId 포함)</li>
 *   <li>1pass: id → MenuNodeDto Map 생성</li>
 *   <li>2pass: parentId 보고 루트 또는 부모.children 에 연결</li>
 *   <li>sortOrder 로 형제 메뉴 정렬</li>
 * </ol>
 *
 * <p>[평면 DB row 예]
 * <pre>
 * id=2, parentId=null  → "환자 관리" (루트)
 * id=3, parentId=2     → "환자 목록" (id=2 의 자식)
 * </pre>
 */
@Service
public class MenuService {

    private final MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    /** 활성 메뉴 전체를 트리 구조로 반환 */
    public List<MenuNodeDto> getMenuTree() {
        return buildTreeFromFlatList(menuMapper.selectAllMenus());
    }

    /**
     * 평면 List&lt;Menu&gt; → 루트 MenuNodeDto 리스트 (children 중첩).
     *
     * <p>1pass — 모든 row 를 DTO 로 만들어 Map(id → node) 에 저장
     * <p>2pass — parentId == null 이면 roots, 아니면 map.get(parentId).children.add(node)
     */
    private List<MenuNodeDto> buildTreeFromFlatList(List<Menu> flatList) {
        Map<Long, MenuNodeDto> nodeById = new HashMap<>();
        Map<Long, Integer> sortOrderById = new HashMap<>();
        List<MenuNodeDto> roots = new ArrayList<>();

        for (Menu menu : flatList) {
            nodeById.put(menu.getId(), toDto(menu));
            sortOrderById.put(menu.getId(), menu.getSortOrder() != null ? menu.getSortOrder() : 0);
        }

        for (Menu menu : flatList) {
            MenuNodeDto node = nodeById.get(menu.getId());
            if (menu.getParentId() == null) {
                roots.add(node);
                continue;
            }
            MenuNodeDto parent = nodeById.get(menu.getParentId());
            if (parent != null) {
                parent.getChildren().add(node);
            }
        }

        Comparator<MenuNodeDto> bySortOrder = Comparator.comparing(
                node -> sortOrderById.getOrDefault(node.getId(), 0));
        roots.sort(bySortOrder);
        for (MenuNodeDto node : nodeById.values()) {
            node.getChildren().sort(bySortOrder);
        }

        return roots;
    }

    /** Menu → MenuNodeDto (parentId, sortOrder 는 응답 JSON 에 포함하지 않음) */
    private MenuNodeDto toDto(Menu menu) {
        MenuNodeDto dto = new MenuNodeDto();
        dto.setId(menu.getId());
        dto.setCode(menu.getCode());
        dto.setName(menu.getName());
        dto.setPath(menu.getPath());
        dto.setIcon(menu.getIcon());
        return dto;
    }
}
