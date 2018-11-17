package com.wt.restaurant.controller.customer;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wt.restaurant.entity.Customer;
import com.wt.restaurant.inface.Update;
import com.wt.restaurant.service.customer.ICode2OpenIdServ;
import com.wt.restaurant.service.customer.ICustomerServ;
import com.wt.restaurant.tool.BusinessUtils;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.MapUtils;

@RestController()
@RequestMapping("customer")
public class CustomerCtrl {

	@Autowired()
	private ICustomerServ customerServImpl;
	
	@Autowired()
	private ICode2OpenIdServ code2OpenIdServImpl;
	
	
	@RequestMapping(value="authorization",method=RequestMethod.POST)
	public Map<String,Object> getCustomerId(String code){
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		//1.获取openID
		String openId = code2OpenIdServImpl.getOpenID(code);
		if(null == openId) {//说明code不合法. 一般来说是模拟器...
			map.put("customerid", null);
			map.put(Constants.STATUS, Constants.FAIL);
			return map;
		}
		
		//2根据openID查询是否存在该用户
		Customer user = customerServImpl.getCustomerByOpenId(openId);
		if(null == user) {//说明该用户是首次访问 插入...
			user = new Customer();
			user.setOpenid(openId);
			customerServImpl.saveCustomer(user);
		}
		
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("customerId", user.getId());
		return map;
	}
	
	@RequestMapping(value="customer",method = {RequestMethod.PUT,RequestMethod.POST})
	public Map<String,Object> updateCustomer(	
												@ModelAttribute("customer")
												@Validated(value=Update.class) 
												Customer customer){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.FAIL);
		if(customerServImpl.updateCustomer(customer)) {
			map.put(Constants.STATUS, Constants.SUCCESS);
		}
		return map;
	}

	@ModelAttribute
	public void prepareCustomer(@RequestParam(value="id",required=false)
			Integer id,Map<String,Object> map){
		if(null != id) {
			Customer customer = customerServImpl.getCustomerById(id);
			map.put("customer",customer);
		}
	}
	
	
	
	@RequestMapping(value = "getcustomerbyid",method = RequestMethod.GET)
	public Customer getCustomerById(@RequestParam("id") Integer id){
		return customerServImpl.getCustomerById(id);
	}
	
	/**
	 * 这里注入session的目的是为了让Tomcat生成JSESSIONID,
	 * 这也是小程序端和后端的入口
	 * @param session
	 * @return
	 */
	@RequestMapping(value = {"/manager","/back/manager"},method = RequestMethod.GET)
	public Map<String,Object> listWeChatEndManager(HttpSession session){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("managerList", Constants.managerList);
		return map;
	}
	
	@RequestMapping(value = "/back/amanager",method = RequestMethod.POST)
	public Map<String,Object> addCustomerID2ManagerList(
			@RequestParam(value="icbxd",required = true) Integer customerID){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Set<Integer> managerlist = Constants.managerList;
		synchronized(this) {//controller 本身就是单例模式 
			if(managerlist.add(customerID))
				map.put(Constants.STATUS, Constants.SUCCESS);
			else
				BusinessUtils.throwNewBusinessException(customerID + "已经是管理员,无需重复添加");
		}
		return map;
	}
	
	@RequestMapping(value = "/back/dmanager",method = RequestMethod.POST)
	public Map<String,Object> removeCustomerIDFromManagerList(
			@RequestParam(value="icbxd",required = true) Integer customerID){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		Set<Integer> managerlist = Constants.managerList;
		synchronized(this) {//controller 本身就是单例模式 
			if(managerlist.remove(customerID))
				map.put(Constants.STATUS, Constants.SUCCESS);
			else
				BusinessUtils.throwNewBusinessException("移除失败,请确认" + customerID + "是管理员");
		}
		return map;
	}
}
