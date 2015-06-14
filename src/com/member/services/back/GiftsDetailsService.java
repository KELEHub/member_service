package com.member.services.back;

import java.util.List;

import com.member.beans.back.enumData.BatchNoEnum;
import com.member.entity.SendGiftsDetails;

public interface GiftsDetailsService {
	
	public List<SendGiftsDetails> getGiftsDetailsList(Integer countNumber,BatchNoEnum batchNo);
	
	public String getCountGoldAll(Integer countNumber,BatchNoEnum batchNo);
	
	public void senGold(SendGiftsDetails ss);
	

}
