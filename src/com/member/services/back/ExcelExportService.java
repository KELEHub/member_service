package com.member.services.back;

import java.io.InputStream;
import java.util.List;

import com.member.entity.Charge;
import com.member.entity.Withdrawals;

public interface ExcelExportService {
	public InputStream exportWithdrawals(List<Withdrawals> list) throws Exception;
	public InputStream exportCharge(List<Charge> list) throws Exception;
}
