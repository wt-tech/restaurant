package com.wt.restaurant.servimpl.menu;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wt.restaurant.dao.menu.IMenuMapper;
import com.wt.restaurant.entity.Menu;
import com.wt.restaurant.entity.MenuImage;
import com.wt.restaurant.service.menu.IMenuService;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.ImageUtils;

@Service
public class MenuServiceImpl implements IMenuService {
	@Autowired
	private IMenuMapper menumapper;

	@Override
	public List<Menu> listMenu(Integer classificationId) {
		// TODO Auto-generated method stub
		return menumapper.listMenu(classificationId);
	}

	@Override
	public boolean updateMenu(Menu menu, MultipartFile file, String staticsPath) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		flag = menumapper.updateMenu(menu) > 0;
		if (flag) {
			return saveupdateImage(menu, file, staticsPath);
		}
		return flag;
	}

	public boolean saveupdateImage(Menu menu, MultipartFile file, String staticsPath) throws Exception {
		boolean flag = true;
		if (null != file && !file.isEmpty()) {
			int id = (int) new Date().getTime();
			// 获取文件名
			String suffix = ImageUtils.getImageTypeWithDot(file);
			// 根据传递的公共路径（前半部分）+表名+id+文件名生成存储路径
			String absolutePath = ImageUtils.generateAbsoluteImgPath(staticsPath, Constants.MENU_IMG, id, suffix);
			// 上传图片
			flag = ImageUtils.saveImage(file, absolutePath);
			// 生成网络访问的路径
			String url = ImageUtils.genrateVirtualImgPath(Constants.MENU_IMG, id, suffix);
			if (flag) {
				MenuImage menuimage = new MenuImage();
				menuimage.setMenu(menu);
				String imgName = menu.getName();
				menuimage.setName(imgName);
				menuimage.setUrl(url);
				flag = menumapper.saveMenuImage(menuimage) > 0;
			}
		}
		return flag;
	}

	@Override
	public boolean saveMenu(Menu menu, MultipartFile file, String staticsPath) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		flag = menumapper.saveMenu(menu) > 0;
		if (flag) {
			return saveupdateImage(menu, file, staticsPath);
		}
		return flag;
	}

	@Override
	public boolean removeMenu(int id) throws Exception {
		// TODO Auto-generated method stub
		return menumapper.removeMenu(id) > 0;
	}

	@Override
	public Menu getMenu(int id) {
		// TODO Auto-generated method stub
		return menumapper.getMenu(id);
	}

	@Override
	public Integer countMenu(Integer classificationId) {
		// TODO Auto-generated method stub
		return menumapper.countMenu(classificationId);
	}

}
