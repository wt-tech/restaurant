package com.wt.restaurant.servimpl.combo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wt.restaurant.dao.combo.IComboMapper;
import com.wt.restaurant.entity.Combo;
import com.wt.restaurant.entity.ComboImage;
import com.wt.restaurant.service.combo.IComboService;
import com.wt.restaurant.tool.Constants;
import com.wt.restaurant.tool.ImageUtils;

@Service
public class ComboServiceImpl implements IComboService {
	@Autowired
	private IComboMapper combomapper;

	@Override
	public List<Combo> listCombo(Integer currentPageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return combomapper.listCombo(currentPageNo, pageSize);
	}

	@Override
	public boolean updateCombo(Combo combo) throws Exception {
		// TODO Auto-generated method stub
		return combomapper.updateCombo(combo) > 0;
	}

	@Override
	public boolean saveCombo(Combo combo, MultipartFile[] file, String staticsPath) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		flag = combomapper.saveCombo(combo) > 0;
		if (flag) {
			if (null != file && file.length > 0) {
				for (int i = 0; i < file.length; i++) {
					MultipartFile attach = file[i];
					if (!attach.isEmpty()) {
						int id = (int) new Date().getTime();
						// 获取文件名
						String suffix = ImageUtils.getImageTypeWithDot(attach);
						// 根据传递的公共路径（前半部分）+表名+id+文件名生成存储路径
						String absolutePath = ImageUtils.generateAbsoluteImgPath(staticsPath, Constants.COMBO_IMG, id,
								suffix);
						flag = false;
						// 上传图片
						flag = ImageUtils.saveImage(attach, absolutePath);
						// 生成网络访问的路径
						String url = ImageUtils.genrateVirtualImgPath(Constants.COMBO_IMG, id, suffix);
						if (flag) {
							ComboImage comboimage = new ComboImage();
							comboimage.setCombo(combo);
							String imgName = combo.getComboName() + i;
							comboimage.setName(imgName);
							comboimage.setUrl(url);
							flag = combomapper.saveComboImage(comboimage) > 0;
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
	public boolean removeCombo(int id) throws Exception {
		// TODO Auto-generated method stub
		return combomapper.removeCombo(id) > 0;
	}

	@Override
	public Combo getCombo(int id) {
		// TODO Auto-generated method stub
		return combomapper.getCombo(id);
	}

	@Override
	public Integer countCombo() {
		// TODO Auto-generated method stub
		return combomapper.countCombo();
	}

}
