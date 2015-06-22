package com.member.services.front;

import java.math.BigDecimal;

import com.member.entity.Information;
import com.member.entity.SystemParameter;

public interface TransferService {

	public void transferManager(Information from,Information to,BigDecimal goldValue,SystemParameter parameter);
	
	public void convertManager(Information info,BigDecimal goldValue,SystemParameter parameter);

}
