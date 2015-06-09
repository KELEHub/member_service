package com.member.services.back;

import java.util.List;

import com.member.entity.BankService;
import com.member.entity.EditeHistory;
import com.member.entity.Information;
import com.member.entity.Institution;

public interface InstitutionService {
	
    public Institution getInstitutionInfo();
	
	public boolean setInstitution(Institution institution);
	
	public List<EditeHistory> getEditeHistoryList();
	
	public List<EditeHistory> getEditeHistoryListByUserId(Integer userId);
	
	public Information getNnmuserByName(String number);
	
	public boolean	savaOrUpdate(Object ob);
	
	public List<BankService> getBankServiceInfo();
	
	public BankService getBankServiceInfoByName(String name);
	
	public void deleteData(Object ob);

}
