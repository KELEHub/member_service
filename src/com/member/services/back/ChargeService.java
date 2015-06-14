package com.member.services.back;

import java.util.List;

import com.member.entity.Charge;
import com.member.form.ChargeOperForm;
import com.member.helper.BaseResult;

public interface ChargeService {

	public List<Charge> getChargeList(ChargeOperForm form);
	
	public Charge getChargeDetailById(Integer id);
	
	public BaseResult<Void> agreeCharge(ChargeOperForm form);
	
	public void saveOrderList(ChargeOperForm form);
	
	public void updateOrderList(ChargeOperForm form);
	
	public void deleteOrderList(Integer id);
}
