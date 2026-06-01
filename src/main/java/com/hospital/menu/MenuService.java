package com.hospital.menu;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/** DB에서 조회한 평면 메뉴 목록을 계층 트리로 변환하는 서비스 */
@Service
public class MenuService {

    private final MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    /** 활성 메뉴 전체를 트리 구조로 반환 */
    public List<MenuNodeDto> getMenuTree() {
        return buildTree(menuMapper.selectAllMenus(), null);
    }

    /**
     * parentId에 해당하는 자식 메뉴를 재귀적으로 찾아 트리를 구성한다.
     * parentId가 null이면 최상위 루트 메뉴부터 시작한다.
     */
    private List<MenuNodeDto> buildTree(List<Menu> flatList, Long parentId) {
        List<MenuNodeDto> result = new ArrayList<>();

        flatList.stream()
                .filter(menu -> Objects.equals(menu.getParentId(), parentId))
                .sorted(Comparator.comparing(
                        menu -> menu.getSortOrder() != null ? menu.getSortOrder() : 0))
                .forEach(menu -> {
                    MenuNodeDto node = toDto(menu);
                    node.setChildren(buildTree(flatList, menu.getId()));
                    result.add(node);
                });

        return result;
    }

    /** 트리 응답용 DTO로 변환 (parentId, sortOrder 제외) */
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
