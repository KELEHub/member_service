package com.member.services.back.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.TestPageDao;
import com.member.entity.TestPageEntity;
import com.member.services.back.TestPageService;

@Service("TestPageServiceImpl")
@Transactional
public class TestPageServiceImpl implements TestPageService {

	@Resource(name = "TestPageDaoImpl")
	private TestPageDao testPageDao;

	@Override
	public List<TestPageEntity> getPageData(String customerPar,int pageSize,int pageNumber) {
		Map<String, Object> arguments = new HashMap<String, Object>();
		String roleQuery = "from TestPageEntity s where 1=1";
		if(customerPar!=null && !"".equals(customerPar)){
			roleQuery+=" and s.testName=:customerPar ";
			arguments.put("customerPar", customerPar);
		}
		//limit表示一次查多少条，offset表示从第几条开始查。
//		roleQuery+=" limit "+limit+" offset "+offset+" ";
		 List result = testPageDao.queryByHql(roleQuery,pageNumber,pageSize,arguments);
		 return result;
	}

	@Override
	public int countData(String customerPar) {
		Map<String, Object> arguments = new HashMap<String, Object>();
		String roleQuery = "from TestPageEntity s where 1=1";
		if(customerPar!=null && !"".equals(customerPar)){
			roleQuery+=" and s.testName=:customerPar ";
			arguments.put("customerPar", customerPar);
		}
		
		List result = testPageDao.queryByHql(roleQuery,arguments);
		if(result!=null){
			return result.size();
		}
		return 0;
	}
	

}
