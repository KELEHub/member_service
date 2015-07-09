package com.member.services.back;

import java.io.InputStream;
import java.util.List;

import com.member.entity.Charge;
import com.member.entity.Withdrawals;
import com.member.form.ChargeOperForm;
import com.member.helper.BaseResult;

public interface ChargeService {

	public List<Charge> getChargeList(ChargeOperForm form);
	
	public Charge getChargeDetailById(Integer id);
	
	public BaseResult<Void> agreeCharge(ChargeOperForm form,String dealUserName);
	
	public BaseResult<Void> disAgreeCharge(ChargeOperForm form,String dealUserName);
	
	public void saveOrderList(ChargeOperForm form);
	
	public void updateOrderList(ChargeOperForm form);
	
	public void deleteOrderList(Integer id);
	
	public InputStream getExportRecords(String memeberNumber) throws Exception;
}
