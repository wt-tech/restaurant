package com.wt.restaurant.dao.classification;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.restaurant.entity.Classification;

public interface IClassificationMapper {
	/**
	 * 查询所有的菜品分类
	 * 
	 * @return
	 */
	List<Classification> listClassification();

	/**
	 * 修改菜品的分类
	 * 
	 * @param classification
	 * @return
	 * @throws Exception
	 */
	Integer updateClassification(Classification classification) throws Exception;

	/**
	 * 新增菜品的分类
	 * 
	 * @param classification
	 * @return
	 * @throws Exception
	 */
	Integer saveClassification(Classification classification) throws Exception;

	/**
	 * 删除菜品的分类
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer removeClassification(@Param("id") int id) throws Exception;

	/**
	 * 查询单个菜品的分类
	 * 
	 * @param id
	 * @return
	 */
	Classification getClassification(@Param("id") int id);

}
