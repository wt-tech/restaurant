package com.wt.restaurant.service.combo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wt.restaurant.entity.Combo;

public interface IComboService {
	/**
	 * 查询所有的套餐
	 * 
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<Combo> listCombo(Integer currentPageNo, Integer pageSize);
	List<Combo> listAllCombo();

	/**
	 * 修改套餐
	 * 
	 * @param combo
	 * @return
	 * @throws Exception
	 */
	boolean updateCombo(Combo combo, MultipartFile file, String staticsPath) throws Exception;

	/**
	 * 新增套餐
	 * 
	 * @param combo
	 * @return
	 * @throws Exception
	 */
	boolean saveCombo(Combo combo, MultipartFile file, String staticsPath) throws Exception;

	/**
	 * 删除套餐
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean removeCombo(int id) throws Exception;

	/**
	 * 查询单个套餐的详情
	 * 
	 * @param id
	 * @return
	 */
	Combo getCombo(int id);

	/**
	 * 查询所有套餐的数量
	 * 
	 * @return
	 */
	Integer countCombo();

}
