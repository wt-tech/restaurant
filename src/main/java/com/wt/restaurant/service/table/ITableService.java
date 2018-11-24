package com.wt.restaurant.service.table;

import java.util.List;

import com.wt.restaurant.entity.Table;

public interface ITableService {
	/**
	 * 查询所有的桌子
	 * 
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	List<Table> listTable(Integer currentPageNo, Integer pageSize,String number);

	/**
	 * 修改桌子信息
	 * 
	 * @param table
	 * @return
	 * @throws Exception
	 */
	boolean updateTable(Table table) throws Exception;

	/**
	 * 保存桌子信息
	 * 
	 * @param table
	 * @return
	 * @throws Exception
	 */
	boolean saveTable(Table table) throws Exception;

	/**
	 * 删除桌子信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	boolean removeTable(int id) throws Exception;

	/**
	 * 获取单个桌子的信息
	 * 
	 * @param id
	 * @return
	 */
	Table getTable(int id);

	/**
	 * 查看单个桌子的信息
	 * @param tableNumber 桌子号
	 * @return
	 */
	Table getTableByTableNumber(String tableNumber);
	
	
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
	Integer countTable(String number);

}
