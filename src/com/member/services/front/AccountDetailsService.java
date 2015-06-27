package com.member.services.front;

import java.util.List;
import java.util.Map;

import com.member.beans.back.enumData.ProjectEnum;
import com.member.entity.AccountDetails;
import com.member.form.back.StatisticsForm;

public interface AccountDetailsService {

	public AccountDetails  getAccountDetailsByUserNumber(String userNumber);

	public List<AccountDetails>  getAccountDetailsByNoservicepoints(String condition,List argumenrs);
	
	public List<AccountDetails>  getAccountDetailsByservicepoints(String condition,List argumenrs);
	
	public List<StatisticsForm>  getAccountDetailsGroupBy(ProjectEnum servicepointsforone,ProjectEnum recharge,ProjectEnum fromgifts,ProjectEnum togoldmoneycut);

	public Map<String, Integer> getCountAll(
			ProjectEnum servicepointsforone, ProjectEnum recharge,
			ProjectEnum fromgifts, ProjectEnum togoldmoneycut);
}
