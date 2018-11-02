package com.wt.restaurant.servimpl.classification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.restaurant.dao.classification.IClassificationMapper;
import com.wt.restaurant.entity.Classification;
import com.wt.restaurant.service.classification.IClassificationService;

@Service
public class ClassificationServiceImpl implements IClassificationService {
	@Autowired
	private IClassificationMapper classificationmapper;

	@Override
	public List<Classification> listClassification() {
		// TODO Auto-generated method stub
		return classificationmapper.listClassification();
	}

	@Override
	public boolean updateClassification(Classification classification) throws Exception {
		// TODO Auto-generated method stub
		return classificationmapper.updateClassification(classification)>0;
	}

	@Override
	public boolean saveClassification(Classification classification) throws Exception {
		// TODO Auto-generated method stub
		return classificationmapper.saveClassification(classification)>0;
	}

	@Override
	public boolean removeClassification(int id) throws Exception {
		// TODO Auto-generated method stub
		return classificationmapper.removeClassification(id)>0;
	}

	@Override
	public Classification getClassification(int id) {
		// TODO Auto-generated method stub
		return classificationmapper.getClassification(id);
	}

}
