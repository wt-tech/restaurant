package com.wt.restaurant.dao.box;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.restaurant.entity.Box;
import com.wt.restaurant.entity.BoxImage;

public interface IBoxMapper {
	/**
	 * 查询所有的包厢
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<Box> listBox(@Param("currentPageNo") Integer currentPageNo, @Param("pageSize") Integer pageSize,@Param("roomNumber") Integer roomNumber);

	/**
	 * 修改包厢信息
	 * @param box
	 * @return
	 * @throws Exception
	 */
	Integer updateBox(Box box) throws Exception;

	/**
	 * 修改包厢图片
	 * @param boximage
	 * @return
	 * @throws Exception
	 */
	Integer updateBoxImage(BoxImage boximage) throws Exception;

	/**
	 * 新增包厢信息
	 * @param box
	 * @return
	 * @throws Exception
	 */
	Integer saveBox(Box box) throws Exception;

	/**
	 * 新增包厢图片
	 * @param boximage
	 * @return
	 * @throws Exception
	 */
	Integer saveBoxImage(BoxImage boximage) throws Exception;

	/**
	 * 删除包厢
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer removeBox(@Param("id") int id) throws Exception;

	/**
	 * 查看单个包厢的信息
	 * @param id
	 * @return
	 */
	Box getBox(@Param("id") int id);

	/**
	 * 查询所有包厢的数量
	 * @return
	 */
	Integer countBox(@Param("roomNumber") Integer roomNumber);

}
