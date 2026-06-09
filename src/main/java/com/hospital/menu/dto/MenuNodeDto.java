package com.hospital.menu.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MenuNodeDto {

    private Long id;
    private String code;
    private String name;
    private String path;
    private String icon;
    private List<MenuNodeDto> children = new ArrayList<>();
}
