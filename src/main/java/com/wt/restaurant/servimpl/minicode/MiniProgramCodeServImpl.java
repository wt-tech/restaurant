package com.wt.restaurant.servimpl.minicode;

import java.io.File;
import java.io.IOException;

import javax.imageio.stream.FileImageOutputStream;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wt.restaurant.entity.MiniProgramCodeParam;
import com.wt.restaurant.service.minicode.IMiniProgramCodeServ;
import com.wt.restaurant.tool.BusinessUtils;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.ImageUtils;
import com.wt.restaurant.tool.http.HttpRequest;
import com.wt.restaurant.tool.http.requestcontenttype.ApplicationJsonContentType;
import com.wt.restaurant.tool.http.responsehandler.BinaryDataResponseHandler;

@Service
public class MiniProgramCodeServImpl implements IMiniProgramCodeServ {

	@Override
	public String fetchAccessToken() {
		
		
		HttpRequest httpReq = new HttpRequest(Constants.ACCESS_TOKEN_URL);
		httpReq.connectAndFetchResult();
		String resString  = (String) httpReq.getResponseContent();
		return resString;
		
	}
	
	@Override
	public boolean fetchWXACodeUnlimit(MiniProgramCodeParam param,String absoluteDirectory) {
		if(null == param)//参数为空直接返回false
			return false;
		if(checkMiniProgramCodeExist(param,absoluteDirectory))//如果该小程序码之前生成过,直接返回true,无需再次生成
			return true;
		//发送post请求,向微信服务器获取小程序码
		HttpRequest httpReq = new HttpRequest(Constants.MINI_PROGRAM_URL+param.getAccess_token());
		httpReq.setContentType(new ApplicationJsonContentType(this.prepareParams(param)));
		httpReq.setResponseHandler(new BinaryDataResponseHandler());
		httpReq.connectAndFetchResult();
		Object content = httpReq.getResponseContent();
		//System.err.println(content);
		//获取成功后,将二进制流小程序码生成图片保存在服务器上
		return this.saveBytes2Image((byte[])content,absoluteDirectory + ImageUtils.getMiniQRAbsoluteURI(param));
	}
	
	private boolean saveBytes2Image(byte[] bytes,String path) {
		File file = new File(path);
		if(!this.checkFileAvaliable(file)) return false;
		
		try(FileImageOutputStream imgOS = new FileImageOutputStream(file)) {
			imgOS.write(bytes);
			imgOS.flush();
		} catch (IOException e) {
			BusinessUtils.throwNewBusinessException("将小程序码二进制流保存成图片发生错误 : "+e.getMessage());
		}
		return true;
	}
	
	private Object prepareParams (MiniProgramCodeParam param) {
		JSONObject jsonObj = (JSONObject) JSON.toJSON(param);
		jsonObj.remove("access_token");
		jsonObj.remove("imgName");
		return jsonObj;
	}

	private boolean checkFileAvaliable(File file) {
		if(file.isDirectory()) {
			return false;
		}
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();//mkdirs会创建所有目录,文件名称也会当做目录来创建
		}
		return true;
	}
	
	/**
	 * 检查该小程序码之前是否生成过
	 * @param param
	 * @return
	 */
	private boolean checkMiniProgramCodeExist(MiniProgramCodeParam param,String absoluteDirectory) {
		String absolutePath = absoluteDirectory + ImageUtils.getMiniQRAbsoluteURI(param);
		File file = new File(absolutePath);
		return file.exists();
	}
}
