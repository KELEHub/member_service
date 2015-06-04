package com.member.services.back;

import java.util.List;

import com.member.entity.Information;

public interface ServiceManagerService {

	List<Information> getServiceByIsService(Integer isService);

	void setTickling(Information information);

}
