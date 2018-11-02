package com.wt.restaurant.service.classification;

import java.util.List;

import com.wt.restaurant.entity.Classification;

public interface IClassificationService {

	List<Classification> listClassification();

	boolean updateClassification(Classification classification) throws Exception;

	boolean saveClassification(Classification classification) throws Exception;

	boolean removeClassification(int id) throws Exception;

	Classification getClassification(int id);

}
