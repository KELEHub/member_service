package com.member.services.back;

import com.member.entity.Information;

public interface InformationService {
	
	public Information getInformationByNumber(String number);
	
	public Information getInformationById(Integer id);
	
}
