package com.hospital.menu.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Menu {

    private Long id;
    private String code;
    private String name;
    private String path;
    private String icon;
    private Long parentId;
    private Integer sortOrder;
}
