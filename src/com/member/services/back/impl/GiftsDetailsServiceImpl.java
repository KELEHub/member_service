package com.member.services.back.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.member.beans.back.enumData.BatchNoEnum;
import com.member.dao.GiftsDao;
import com.member.entity.SendGiftsDetails;
import com.member.services.back.GiftsDetailsService;
@Service("GiftsDetailsServiceImpl")
public class GiftsDetailsServiceImpl implements GiftsDetailsService{

	
	@Resource(name = "GiftsDaoImpl")
	private GiftsDao giftsDao;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<SendGiftsDetails> getGiftsDetailsList(Integer countNumber,
			BatchNoEnum batchNo) {
		String hql="from SendGiftsDetails mr where mr.countNumber<? and  mr.batchNo=? and mr.stauts=0";
		List arguments = new ArrayList();
		arguments.add(countNumber);
		arguments.add(batchNo);
		List<SendGiftsDetails> result = (List<SendGiftsDetails>)giftsDao.queryByHql(hql,arguments);
		if(result!=null && result.size()>0){
			return result;
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String getCountGoldAll(Integer countNumber, BatchNoEnum batchNo) {
		String sql="select SUM(mr.goldMoney) from SendGiftsDetails mr where mr.countNumber<? and  mr.batchNo=? and mr.stauts=0";
		List arguments = new ArrayList();
		arguments.add(countNumber);
		arguments.add(batchNo);
		List<?> result = giftsDao.queryByHql(sql,arguments);
		if(result!=null && result.size()>0){
			return result.get(0).toString();
		}
		
		return null;
	}
}
