package com.member.services.back.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.HqlTicklingManager;
import com.member.dao.TicklingManagerDao;
import com.member.entity.Tickling;
import com.member.form.back.TickForm;
import com.member.services.back.TicklingManagerService;

@SuppressWarnings("unchecked")
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
	@Transactional(readOnly=true)
	public List<Tickling> getTicklingById(Integer id) {
		return (List<Tickling>) ticklingManagerDao.queryByHql(
				HqlTicklingManager.getTicklingById, id);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void setTickling(Tickling tickling) {
		ticklingManagerDao.saveOrUpdate(tickling);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTickling(TickForm form) {
		String hql = "delete from Tickling where id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(Integer.parseInt(form.getId()));
		ticklingManagerDao.executeHqlUpdate(hql, list);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTickling(TickForm form) {
		String hql = "update Tickling set replyContent=?,replyDate=?,state=? where id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(form.getReplyContent());
		list.add(new Date());
		list.add(1);
		list.add(Integer.parseInt(form.getId()));
		ticklingManagerDao.executeHqlUpdate(hql, list);
	}
}
