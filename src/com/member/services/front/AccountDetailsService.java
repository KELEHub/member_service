package com.member.services.front;

import java.util.List;

import com.member.entity.AccountDetails;

public interface AccountDetailsService {

	public AccountDetails  getAccountDetailsByUserNumber(String userNumber);

	public List<AccountDetails>  getAccountDetailsByNoservicepoints(String condition,List argumenrs);
	
	public List<AccountDetails>  getAccountDetailsByservicepoints(String condition,List argumenrs);


}
