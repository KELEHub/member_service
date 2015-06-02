package com.member.services.back;

import com.member.beans.back.ParameterBean;
import com.member.entity.SystemParameter;

public interface ParameterService {
	public SystemParameter getSystemParameter();
	
	public boolean setSystemParameter(SystemParameter systemParameter,ParameterBean pb);
}
