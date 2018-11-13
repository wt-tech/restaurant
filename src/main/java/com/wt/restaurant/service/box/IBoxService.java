package com.wt.restaurant.service.box;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wt.restaurant.entity.Box;

public interface IBoxService {
	/**
	 * 查询所有的包厢
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<Box> listBox(Integer currentPageNo,Integer pageSize);

	/**
	 * 修改包厢信息
	 * @param box
	 * @return
	 * @throws Exception
	 */
	boolean updateBox(Box box, MultipartFile file, String staticsPath) throws Exception;


	/**
	 * 新增包厢信息
	 * @param box
	 * @return
	 * @throws Exception
	 */
	boolean saveBox(Box box, MultipartFile file, String staticsPath) throws Exception;

	/**
	 * 删除包厢
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean removeBox(int id) throws Exception;

	/**
	 * 查看单个包厢的信息
	 * @param id
	 * @return
	 */
	Box getBox(int id);

	/**
	 * 查询所有包厢的数量
	 * @return
	 */
	Integer countBox();

}
