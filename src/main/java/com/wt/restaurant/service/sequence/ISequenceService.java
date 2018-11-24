package com.wt.restaurant.service.sequence;

/**
 * 获取订单的流水号
 * @author Daryl
 */
public interface ISequenceService {

	/**
	 * 获取下一个流水号
	 * @return
	 */
	public String updateAndGetNextSequence();
}
