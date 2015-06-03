package com.member.services.back.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.HqlInstitution;
import com.member.dao.InstitutionDao;
import com.member.entity.Institution;
import com.member.services.back.InstitutionService;
@Service("InstitutionServiceImpl")
public class InstitutionServiceImpl implements InstitutionService{
	
	

	@Resource(name = "InstitutionDaoImpl")
    InstitutionDao institutionDao;
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Institution getInstitutionInfo() {
		List<Institution> dataPermissionCodes=(List<Institution>) institutionDao.queryByHql(HqlInstitution.getInstitutionInfo);
        if(dataPermissionCodes!=null && dataPermissionCodes.size()>0){
        	return dataPermissionCodes.get(0);
        }
        return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean setInstitution(Institution institution) {
		
		try {
			institutionDao.saveOrUpdate(institution);
			
	        return true;
		} catch (Exception e) {
			return false;
		}
		
	}

}
