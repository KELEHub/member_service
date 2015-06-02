package com.member.service.managementService.systemmanager.parameter.service;

import com.member.service.managementService.systemmanager.parameter.bean.ParameterBean;
import com.member.service.managementService.systemmanager.parameter.po.SystemParameter;

public interface ParameterService {
	public SystemParameter getSystemParameter();
	
	public boolean setSystemParameter(SystemParameter systemParameter,ParameterBean pb);
}
