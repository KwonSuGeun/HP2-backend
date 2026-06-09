package com.hospital.service;

import com.hospital.dto.MenuNodeDto;

import java.util.List;

public interface MenuService {

    List<MenuNodeDto> getMenuTree();
}
