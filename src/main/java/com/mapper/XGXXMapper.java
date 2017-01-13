package com.mapper;

import java.util.List;

import com.bean.DA02Data;
import com.bean.LsjData;
import com.bean.WorkItem;

public interface XGXXMapper {

	List<LsjData> selectLsjData(String wth);
	
	List<WorkItem> getLsjWorkItem(String lsh);
	
	List<DA02Data> selectDA02Data(String wth);
	
	List<WorkItem> getDA02WorkItem(String lsh);
}
