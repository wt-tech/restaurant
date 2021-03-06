package com.wt.restaurant.service.menu;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wt.restaurant.entity.Menu;

public interface IMenuService {
	/**
	 * 查询指定分类下的菜品
	 * 
	 * @param classificationId
	 * @param currentPageNos 
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<Menu> listMenu(Integer classificationId, Integer currentPageNos,String name);

	/**
	 * 修改菜品信息
	 * 
	 * @param Menu
	 * @return
	 * @throws Exception
	 */
	boolean updateMenu(Menu Menu,MultipartFile file,String staticsPath) throws Exception;

	/**
	 * 保存菜品信息
	 * 
	 * @param Menu
	 * @return
	 * @throws Exception
	 */
	boolean saveMenu(Menu Menu,MultipartFile file,String staticsPath) throws Exception;

	/**
	 * 删除菜品
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean removeMenu(int id) throws Exception;

	/**
	 * 查询单个菜品的信息
	 * 
	 * @param id
	 * @return
	 */
	Menu getMenu(int id);

	/**
	 * 获取指定分类下的所有菜品数量
	 * 
	 * @param classificationId
	 * @return
	 */
	Integer countMenu(Integer classificationId,String name);
	
	/**
	 * 根据菜品名模糊查询
	 * @param name
	 * @return
	 */
	List<Menu> listMenuByName(String name,Integer pageNo,Integer pagesizes);
	
	/**
	 * 根据菜品名模糊查询总个数
	 * @param name
	 * @return
	 */
	Integer listMenuCountByName( String name);
}
