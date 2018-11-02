package com.wt.restaurant.dao.banner;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.restaurant.entity.Banner;

public interface IBannersMapper {
	/**
	 * 查询所有的轮播图
	 * 
	 * @return
	 */
	List<Banner> listBanner();

	/**
	 * 修改轮播图
	 * 
	 * @param banner
	 * @return
	 * @throws Exception
	 */
	Integer updateBanner(Banner banner) throws Exception;

	/**
	 * 添加轮播图
	 * 
	 * @param banner
	 * @return
	 * @throws Exception
	 */
	Integer saveBanner(Banner banner) throws Exception;

	/**
	 * 删除轮播图
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer removeBanner(@Param("id") int id) throws Exception;

	/**
	 * 查看单张轮播图
	 * 
	 * @param id
	 * @return
	 */
	Banner getBanner(@Param("id") int id);

	/**
	 * 获取轮播图的数量
	 * 
	 * @return
	 */
	Integer countBanner();
}
