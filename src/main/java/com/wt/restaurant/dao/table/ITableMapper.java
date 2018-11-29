package com.wt.restaurant.dao.table;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.restaurant.entity.Table;

public interface ITableMapper {
	/**
	 * 查询所有的桌子
	 * 
	 * @param currentPageNo
	 * @param pageSize
	 * @param number 
	 * @return
	 */
	List<Table> listTables(@Param("currentPageNo") Integer currentPageNo, 
			@Param("pageSize") Integer pageSize, @Param("number") String number);

	/**
	 * 修改桌子信息
	 * 
	 * @param table
	 * @return
	 * @throws Exception
	 */
	Integer updateTables(Table table) throws Exception;

	/**
	 * 保存桌子信息
	 * 
	 * @param table
	 * @return
	 * @throws Exception
	 */
	Integer saveTable(Table table) throws Exception;

	/**
	 * 删除桌子信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer removeTable(@Param("id") int id) throws Exception;

	/**
	 * 获取单个桌子的信息
	 * 
	 * @param id
	 * @return
	 */
	Table getTable(@Param("id") int id);

	/**
	 * 查看单个桌子的信息
	 * @param tableNumber 桌子号
	 * @return
	 */
	Table getTableByTableNumber(@Param("tableNumber") String tableNumber);
	
	
	/**
	 * 查询所有桌子的数量
	 * 
	 * @return
	 */
	Integer countTable();
	
	/**
	 * 查询桌号like number 的桌子的数量
	 * @return
	 */
	Integer countTableByName(@Param("number") String number);
}
