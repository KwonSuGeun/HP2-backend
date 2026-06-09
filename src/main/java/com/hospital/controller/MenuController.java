package com.hospital.controller;

import com.hospital.dto.MenuNodeDto;
import com.hospital.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
@CrossOrigin(originPatterns = {"http://localhost:3000", "http://127.0.0.1:3000"})
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public List<MenuNodeDto> getMenus() {
        return menuService.getMenuTree();
    }
}
