package com.wt.restaurant.tool;

import java.util.ArrayList;
import java.util.List;

import com.wt.restaurant.entity.Box;
import com.wt.restaurant.entity.Reserve;

public class CompareBox {
	// 查询出午餐预订的包厢
	public static List<Box> LuncheonReserve(List<Reserve> reserve) {
		List<Box> singlelistbox = new ArrayList<Box>();
		for (int i = 0; i < reserve.size(); i++) {
			if ("午餐预订".equals(reserve.get(i).getReservationType())) {
				for (Box box2 : reserve.get(i).getBox()) {
					singlelistbox.add(box2);// 午餐预订的包厢
				}
			}
		}
		return singlelistbox;
	}
     
	//查询晚餐预订的包厢
	public static List<Box> DinnerReserve(List<Reserve> reserve) {
		List<Box> pairlistbox = new ArrayList<Box>();
		for (int i = 0; i < reserve.size(); i++) {
			if ("晚餐预订".equals(reserve.get(i).getReservationType())) {
				for (Box box2 : reserve.get(i).getBox()) {
					pairlistbox.add(box2);// 晚餐预订的包厢
				}
			}
		}
		return pairlistbox;
	}

	public static List<Box> LuncheonDinnerReserve(List<Box> singlelistbox, List<Box> pairlistbox, List<Box> box) {
		List<Box> singlepairlistbox = new ArrayList<Box>();
		for (Box box2 : singlelistbox) {//遍历午餐预订的包厢
			if (pairlistbox.contains(box2)) {//如果晚餐预订也包含此包厢，则放到新的集合中
				singlepairlistbox.add(box2);// 午餐与晚餐均预订
			}
		}
		singlelistbox.removeAll(singlepairlistbox);//午餐包厢中移除午餐与晚餐均有预订的包厢，也就是说专门存放只有午餐预订的包厢
		pairlistbox.removeAll(singlepairlistbox);//同上
		return CompareBox.SetBoxStatus(singlelistbox, pairlistbox, singlepairlistbox, box);
	}

	public static List<Box> SetBoxStatus(List<Box> singlelistbox, List<Box> pairlistbox, List<Box> singlepairlistbox,
			List<Box> box) {
		for (Box box2 : box) {//遍历要显示的包厢
			if (singlelistbox.contains(box2)) {//如果存放午餐包厢的集合包含此包厢
				box2.setReserveStatus(0);//将其预订转态设置为0
			} else if (pairlistbox.contains(box2)) {//....晚餐.....
				box2.setReserveStatus(1);//........1
			} else if (singlepairlistbox.contains(box2)) {//....午餐和晚餐均有.....
				box2.setReserveStatus(2);//........2
			} else {//否则，即午餐和晚餐均没有预订
				box2.setReserveStatus(3);//......3
			}
		}
		return box;
	}
}
