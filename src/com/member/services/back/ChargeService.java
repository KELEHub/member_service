package com.member.services.back;

import java.io.InputStream;
import java.util.List;

import com.member.entity.Charge;
import com.member.entity.Withdrawals;
import com.member.form.ChargeOperForm;
import com.member.helper.BaseResult;

public interface ChargeService {

	public List<Charge> getNoChargeList(String memeberNumber,int pageSize,int pageNumber);
	
	public List<Charge> getChargeList(String memeberNumber,int pageSize,int pageNumber);
	
	public int countChargeList(String memeberNumber);
	
	public int countNoChargeList(String memeberNumber);
	
	public Charge getChargeDetailById(Integer id);
	
	public BaseResult<Void> agreeCharge(ChargeOperForm form,String dealUserName);
	
	public BaseResult<Void> disAgreeCharge(ChargeOperForm form,String dealUserName);
	
	public void saveOrderList(ChargeOperForm form);
	
	public void updateOrderList(ChargeOperForm form);
	
	public void deleteOrderList(Integer id);
	
	public InputStream getExportRecords(String memeberNumber) throws Exception;
}
