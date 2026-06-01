package com.hospital.menu;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/** AUTH_MENU 테이블 조회 MyBatis 매퍼. SQL은 MenuMapper.xml 참고 */
@Mapper
public interface MenuMapper {

    /** IS_ACTIVE='Y'인 메뉴 전체를 SORT_ORDER 순으로 조회 */
    List<Menu> selectAllMenus();
}
