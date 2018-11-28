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
	public List<Combo> listAllCombo() {
		// TODO Auto-generated method stub
		return combomapper.listAllCombo();
	}

	@Override
	public boolean updateCombo(Combo combo, MultipartFile file, MultipartFile file2, String staticsPath)
			throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		flag = combomapper.updateCombo(combo) > 0;
		if (flag)
			return saveupdateImage(combo, file, file2, staticsPath);
		return flag;
	}

	public boolean saveupdateImage(Combo combo, MultipartFile attach, MultipartFile attach2, String staticsPath)
			throws Exception {
		// TODO Auto-generated method stub
		boolean flag = true;
		if ((null != attach && !attach.isEmpty()) && (null != attach2 && !attach2.isEmpty())) {
			int id = (int) new Date().getTime();
			int id2 = ((int) new Date().getTime())+1;
			// 获取文件名
			String suffix = ImageUtils.getImageTypeWithDot(attach);
			String suffix2 = ImageUtils.getImageTypeWithDot(attach2);
			// 根据传递的公共路径（前半部分）+表名+id+文件名生成存储路径
			String absolutePath = ImageUtils.generateAbsoluteImgPath(staticsPath, Constants.COMBO_IMG, id, suffix);
			String absolutePath2 = ImageUtils.generateAbsoluteImgPath(staticsPath, Constants.COMBO_IMG, id2, suffix2);
			flag = false;
			// 上传图片
			flag = ImageUtils.saveImage(attach, absolutePath);
			flag = ImageUtils.saveImage(attach2, absolutePath2);
			// 生成网络访问的路径
			String url = ImageUtils.genrateVirtualImgPath(Constants.COMBO_IMG, id, suffix);
			String url2 = ImageUtils.genrateVirtualImgPath(Constants.COMBO_IMG, id2, suffix2);
			if (flag) {
				ComboImage comboimage = new ComboImage();
				comboimage.setCombo(combo);
				String imgName = combo.getComboName();
				comboimage.setName(imgName);
				comboimage.setUrl(url);
				comboimage.setUrl2(url2);
				flag = combomapper.saveComboImage(comboimage) > 0;
			}
		}
		return flag;
	}

	@Override
	public boolean saveCombo(Combo combo, MultipartFile file, MultipartFile file2, String staticsPath)
			throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		flag = combomapper.saveCombo(combo) > 0;
		if (flag)
			return saveupdateImage(combo, file, file2, staticsPath);
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
