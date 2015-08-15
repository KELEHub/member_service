package com.member.services.back;

import java.util.List;

import org.snmp4j.smi.Integer32;

import com.member.entity.ApplyService;
import com.member.entity.ForbidForm;
import com.member.entity.Information;
import com.member.form.back.InformationForm;

public interface ServiceManagerService {

	List<Information> getServiceByIsService(Integer isService,String customerPar,
			int pageSize,int pageNumber,String number);

	List<ApplyService> getApplyService(String customerPar,
			int pageSize,int pageNumber);

	Information getServiceByNumber(String number);
	
	Information getServiceById(Integer id);

	void updateInfo(InformationForm msf);

	void forbiddenService(Integer isService, Integer id);

	void updateApplyState(Integer state, Integer id, String failureCause);

	void saveOrUpdate(Object obj);

	ForbidForm getForbidForm();

	void updateForbidForm(Integer ifForbid);

	int countApproveServiceData(String customerPar);

	int countServiceManagerData(String customerPar, Integer isService,String number);

}
