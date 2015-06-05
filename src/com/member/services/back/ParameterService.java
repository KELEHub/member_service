package com.member.services.back;

import com.member.entity.SystemParameter;

public interface ParameterService {
	public SystemParameter getSystemParameter();
	
	public boolean setSystemParameter(SystemParameter systemParameter);
}
