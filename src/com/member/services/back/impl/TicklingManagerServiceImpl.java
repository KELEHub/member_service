package com.member.services.back.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.HqlTicklingManager;
import com.member.dao.TicklingManagerDao;
import com.member.entity.Tickling;
import com.member.services.back.TicklingManagerService;

@Service("TicklingManagerServiceImpl")
public class TicklingManagerServiceImpl implements TicklingManagerService {

	@Resource(name = "TicklingManagerDaoImpl")
    TicklingManagerDao ticklingManagerDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Tickling> getTicklingByState(Integer state) {
		return (List<Tickling>) ticklingManagerDao.queryByHql(
				HqlTicklingManager.getTickling, state);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void setTickling(Tickling tickling) {
		ticklingManagerDao.saveOrUpdate(tickling);
	}
}
