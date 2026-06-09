package com.hospital.menu.service;

import com.hospital.menu.dto.MenuNodeDto;

import java.util.List;

public interface MenuService {

    List<MenuNodeDto> getMenuTree();
}
