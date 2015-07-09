package com.member.services.back;

import java.util.List;

import com.member.entity.Information;
import com.member.entity.Institution;

public interface InformationService {
	
	public Information getInformationByNumber(String number);
	
	public Information getInformationById(Integer id);
	
	public List<Information> getInformationForNoActivate(String number);
	
	public void deleteData(Information info);
	
	public boolean activate(Information info,Information selfInfo,Institution institution, Information recommendInfo);
	
	public void saveOrUpfate(Information info);
	
	public int countBankCard(String card);
	
	public void serverRigster(Information info, Information selfInfo,Institution institution, Information recommendInfo );
	
}
