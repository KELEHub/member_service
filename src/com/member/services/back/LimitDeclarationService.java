package com.member.services.back;

import java.util.Date;

import com.member.beans.back.enumData.ProjectEnum;
import com.member.entity.LimitDeclaration;

public interface LimitDeclarationService {
	
	public LimitDeclaration getLimitDeclarationInfo();
	
	public int getCountThisWeek(Date date,ProjectEnum project);
	
	public int getCountAll(ProjectEnum project);
	
	public boolean saveOrUpdate(Object ob);
	
	public boolean deleteData(Object ob);

}
