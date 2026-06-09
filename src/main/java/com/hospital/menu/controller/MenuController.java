package com.hospital.menu.controller;

import com.hospital.common.ApiResponse;
import com.hospital.menu.dto.MenuNodeDto;
import com.hospital.menu.service.MenuService;
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
    public ApiResponse<List<MenuNodeDto>> getMenuTree() {
        return ApiResponse.success(menuService.getMenuTree());
    }
}
