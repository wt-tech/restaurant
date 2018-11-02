package com.wt.restaurant.tool.http;

import java.io.IOException;
import java.io.InputStream;

import com.wt.restaurant.tool.BusinessUtils;

/**
 * 默认的处理请求的类
 * @author Daryl
 */
public class DefaultResponseHandler extends ResponseHanlder{

	//Object obj = this.connection.getContent();//这货返回的竟然是一个HttpInputStream
	@Override
	public void fetchContent() {
		StringBuilder sb = new StringBuilder("");
		try(InputStream inStream = this.connection.getInputStream()){
			byte[] bts = new byte[1024];
			int len = 0;
			while((len = inStream.read(bts)) != -1) {
				sb.append(new String(bts,0,len));
			}
		} catch (IOException e) {
			BusinessUtils.throwNewBusinessException("获取响应主体内容失败" + e.getMessage());
		}
		this.setContent(sb.toString());
	}
	
}
