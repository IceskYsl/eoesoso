package com.eoesou.service;

import java.util.List;

import com.eoesou.model.AppCategory;
import com.eoesou.model.AppObj;

public interface ISosoService {
	public List<AppCategory> getCategories(int parent_id);
	public List<AppObj> search(String keyWords);
	public AppObj getAppinfo(String id);
}
