package com.hospital.service.impl;

import com.hospital.dto.MenuNodeDto;
import com.hospital.entity.Menu;
import com.hospital.mapper.MenuMapper;
import com.hospital.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;

    @Override
    public List<MenuNodeDto> getMenuTree() {
        return buildTreeFromFlatList(menuMapper.selectAllMenus());
    }

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
