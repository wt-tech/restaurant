package com.wt.restaurant.dao.menu;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.restaurant.entity.Menu;
import com.wt.restaurant.entity.MenuImage;

public interface IMenuMapper {
	/**
	 * 查询指定分类下的菜品
	 * 
	 * @param classificationId
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<Menu> listMenu(@Param("classificationId") Integer classificationId);

	/**
	 * 修改菜品信息
	 * 
	 * @param Menu
	 * @return
	 * @throws Exception
	 */
	Integer updateMenu(Menu Menu) throws Exception;

	/**
	 * 修改菜品图片
	 * 
	 * @param menuimage
	 * @return
	 * @throws Exception
	 */
	Integer updateMenuImage(MenuImage menuimage) throws Exception;

	/**
	 * 保存菜品信息
	 * 
	 * @param Menu
	 * @return
	 * @throws Exception
	 */
	Integer saveMenu(Menu Menu) throws Exception;

	/**
	 * 保存菜品图片
	 * 
	 * @param menuimage
	 * @return
	 * @throws Exception
	 */
	Integer saveMenuImage(MenuImage menuimage) throws Exception;

	/**
	 * 删除菜品
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer removeMenu(@Param("id") int id) throws Exception;

	/**
	 * 查询单个菜品的信息
	 * 
	 * @param id
	 * @return
	 */
	Menu getMenu(@Param("id") int id);

	/**
	 * 获取指定分类下的所有菜品数量
	 * 
	 * @param classificationId
	 * @return
	 */
	Integer countMenu(@Param("classificationId") Integer classificationId);
}
