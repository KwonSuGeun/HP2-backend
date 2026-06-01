package com.hospital.menu;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 사이드바·네비게이션용 메뉴 트리 API.
 * 프론트엔드(Next.js, localhost:3000)에서 호출한다.
 */
@RestController
@RequestMapping("/api/menus")
@CrossOrigin(originPatterns = {"http://localhost:3000", "http://127.0.0.1:3000"})
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /** GET /api/menus — 계층형 메뉴 목록 반환 */
    @GetMapping
    public List<MenuNodeDto> getMenus() {
        return menuService.getMenuTree();
    }
}
