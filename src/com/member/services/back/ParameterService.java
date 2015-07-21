package com.member.services.back;

import java.util.List;

import com.member.entity.GiftsDetails;
import com.member.entity.GiftsHistory;
import com.member.entity.SystemParameter;

public interface ParameterService {
	public SystemParameter getSystemParameter();
	
	public boolean setSystemParameter(SystemParameter systemParameter);
	
	public List<GiftsDetails> getGiftsDetails(String number, Integer pageSize,Integer pageNumber);
	
	public GiftsDetails getGiftsDetailsById(Integer id);
	
	public List<GiftsDetails> getGiftsDetailsByNumber(String number);
	
	public List<GiftsHistory> getGiftsHistoryAll();
	
	public List<GiftsHistory> getGiftsHistoryByOperationId(Integer id);
	
	public void saveOrUpdate(GiftsDetails gd,GiftsHistory gh,Integer pointNumber);
	
	public int  countGiftsDetails(String number);
	
}
