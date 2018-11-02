package com.wt.restaurant.dao.combo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.restaurant.entity.Combo;
import com.wt.restaurant.entity.ComboImage;

public interface IComboMapper {
	/**
	 * 查询所有的套餐
	 * 
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<Combo> listCombo(@Param("currentPageNo") Integer currentPageNo, @Param("pageSize") Integer pageSize);

	/**
	 * 修改套餐
	 * 
	 * @param combo
	 * @return
	 * @throws Exception
	 */
	Integer updateCombo(Combo combo) throws Exception;

	/**
	 * 修改套餐图片
	 * 
	 * @param comboimage
	 * @return
	 * @throws Exception
	 */
	Integer updateComboImage(ComboImage comboimage) throws Exception;

	/**
	 * 新增套餐
	 * 
	 * @param combo
	 * @return
	 * @throws Exception
	 */
	Integer saveCombo(Combo combo) throws Exception;

	/**
	 * 新增套餐图片
	 * 
	 * @param comboimage
	 * @return
	 * @throws Exception
	 */
	Integer saveComboImage(ComboImage comboimage) throws Exception;

	/**
	 * 删除套餐
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer removeCombo(@Param("id") int id) throws Exception;

	/**
	 * 查询单个套餐的详情
	 * 
	 * @param id
	 * @return
	 */
	Combo getCombo(@Param("id") int id);

	/**
	 * 查询所有套餐的数量
	 * 
	 * @return
	 */
	Integer countCombo();

}
