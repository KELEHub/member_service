package com.member.services.back;

import java.util.List;

import com.member.entity.Information;
import com.member.entity.Institution;
import com.member.entity.SystemParameter;

public interface InformationService {
	
	public Information getInformationByNumber(String number);
	
	public Information getInformationById(Integer id);
	
	public List<Information> getInformationForNoActivate(String number);
	
	public void deleteData(Information info);
	
	public void activate(Information info,Information selfInfo,Institution institution );
	
}
