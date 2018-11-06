package com.wt.restaurant.servimpl.table;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.restaurant.dao.table.ITableMapper;
import com.wt.restaurant.entity.Table;
import com.wt.restaurant.service.table.ITableService;
@Service
public class TableServiceImpl implements ITableService {
	@Autowired
	private ITableMapper tablemapper;

	@Override
	public List<Table> listTable(Integer currentPageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return tablemapper.listTables(currentPageNo, pageSize);
	}

	@Override
	public boolean updateTable(Table table) throws Exception {
		// TODO Auto-generated method stub
		return tablemapper.updateTables(table)>0;
	}

	@Override
	public boolean saveTable(Table table) throws Exception {
		// TODO Auto-generated method stub
		return tablemapper.saveTable(table)>0;
	}

	@Override
	public boolean removeTable(int id) throws Exception {
		// TODO Auto-generated method stub
		return tablemapper.removeTable(id)>0;
	}

	@Override
	public Table getTable(int id) {
		// TODO Auto-generated method stub
		return tablemapper.getTable(id);
	}

	@Override
	public Integer countTable() {
		// TODO Auto-generated method stub
		return tablemapper.countTable();
	}

}
