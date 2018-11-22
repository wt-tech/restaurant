package com.wt.restaurant.dao.sequence;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

/**
 * 获取订单的流水号
 * @author Daryl
 */
public interface ISequenceMapper {

	/**
	 * 获取下一个流水号
	 * @return
	 */
	@Select("CALL fetch_next_sequence_number();")
	@Options(statementType=StatementType.CALLABLE)
	public String updateAndGetNextSequence();
}
