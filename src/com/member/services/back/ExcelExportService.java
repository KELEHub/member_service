package com.member.services.back;

import java.io.InputStream;
import java.util.List;

import com.member.entity.Withdrawals;

public interface ExcelExportService {
	public InputStream export(List<Withdrawals> list) throws Exception;
}
