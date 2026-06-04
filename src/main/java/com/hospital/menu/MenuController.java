package com.hospital.menu;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/menus")
@CrossOrigin(originPatterns = {"http://localhost:3000", "http://127.0.0.1:3000"})
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /** 계층형 메뉴 트리 반환 — Service.buildTree() 결과를 JSON 으로 응답 */
    @GetMapping
    public List<MenuNodeDto> getMenus() {
        return menuService.getMenuTree();
    }
}
