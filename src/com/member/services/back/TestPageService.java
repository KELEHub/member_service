package com.member.services.back;

import java.util.List;

import com.member.entity.TestPageEntity;

public interface TestPageService {
   
	public List<TestPageEntity> getPageData(String customerPar,int pageSize,int pageNumber);
	
	public int countData(String customerPar);
}
