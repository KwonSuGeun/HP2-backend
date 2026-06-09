package com.hospital.mapper;

import com.hospital.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<Menu> selectAllMenus();
}
