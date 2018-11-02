package com.wt.restaurant.servimpl.box;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wt.restaurant.dao.box.IBoxMapper;
import com.wt.restaurant.entity.Box;
import com.wt.restaurant.entity.BoxImage;
import com.wt.restaurant.service.box.IBoxService;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.ImageUtils;

@Service
public class BoxServiceImpl implements IBoxService {
	@Autowired
	private IBoxMapper boxmapper;

	@Override
	public List<Box> listBox(Integer currentPageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return boxmapper.listBox(currentPageNo, pageSize);
	}

	@Override
	public boolean updateBox(Box box) throws Exception {
		// TODO Auto-generated method stub
		return boxmapper.updateBox(box) > 0;
	}

	@Override
	public boolean saveBox(Box box, MultipartFile[] file, String staticsPath) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		flag = boxmapper.saveBox(box) > 0;
		if (flag) {
			if (null != file && file.length > 0) {
				for (int i = 0; i < file.length; i++) {
					MultipartFile attach = file[i];
					if (!attach.isEmpty()) {
						int id = (int) new Date().getTime();
						// 获取文件名
						String suffix = ImageUtils.getImageTypeWithDot(attach);
						// 根据传递的公共路径（前半部分）+表名+id+文件名生成存储路径
						String absolutePath = ImageUtils.generateAbsoluteImgPath(staticsPath, Constants.BOX_IMG, id,
								suffix);
						flag = false;
						// 上传图片
						flag = ImageUtils.saveImage(attach, absolutePath);
						// 生成网络访问的路径
						String url = ImageUtils.genrateVirtualImgPath(Constants.BOX_IMG, id, suffix);
						if (flag) {
							BoxImage boximage = new BoxImage();
							boximage.setBox(box);
							String imgName = box.getRoomName() + i;
							boximage.setName(imgName);
							boximage.setUrl(url);
							flag = boxmapper.saveBoxImage(boximage) > 0;
							if (flag)
								continue;
						}
					}
				}
			}
		}

		return flag;
	}

	@Override
	public boolean removeBox(int id) throws Exception {
		// TODO Auto-generated method stub
		return boxmapper.removeBox(id) > 0;
	}

	@Override
	public Box getBox(int id) {
		// TODO Auto-generated method stub
		return boxmapper.getBox(id);
	}

	@Override
	public Integer countBox() {
		// TODO Auto-generated method stub
		return boxmapper.countBox();
	}

}
