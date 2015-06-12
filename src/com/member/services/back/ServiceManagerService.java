package com.member.services.back;

import java.util.List;

import com.member.entity.ApplyService;
import com.member.entity.Information;
import com.member.form.back.InformationForm;

public interface ServiceManagerService {

	List<Information> getServiceByIsService(Integer isService);

	void setTickling(Information information);

	List<ApplyService> getApplyService();

	Information getServiceById(Integer id);

	void updateInfo(InformationForm msf);

}
