package com.hospital.menu;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * CMH.AUTH_MENU 조회 MyBatis 매퍼 인터페이스.
 * SQL 본문은 src/main/resources/mapper/MenuMapper.xml
 *
 * <p>selectAllMenus() 반환 형태: List&lt;Menu&gt;
 * <pre>
 * [
 *   Menu(id=1, parentId=null, code="DASHBOARD", name="대시보드", ...),
 *   Menu(id=2, parentId=null, code="PATIENT",  name="환자 관리", ...),
 *   Menu(id=3, parentId=2,    code="PATIENT_LIST", name="환자 목록", ...)
 * ]
 * </pre>
 * 아직 트리 아님 — MenuService.buildTree() 가 parentId 로 계층화.
 */
@Mapper
public interface MenuMapper {

    /** IS_ACTIVE='Y' 메뉴 전체, SORT_ORDER 순 — 평면 List&lt;Menu&gt; */
    List<Menu> selectAllMenus();
}
