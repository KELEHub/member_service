package com.member.services.back;

import com.member.entity.Information;

public interface InformationService {
	
	public Information getInformationByName(String number);
	
	public Information getInformationById(Integer id);

}
