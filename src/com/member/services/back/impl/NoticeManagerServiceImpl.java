package com.member.services.back.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.HqlNoticeManager;
import com.member.dao.NoticeManagerDao;
import com.member.entity.Notice;
import com.member.form.back.NoticeForm;
import com.member.services.back.NoticeManagerService;

@SuppressWarnings("unchecked")
@Service("NoticeManagerServiceImpl")
public class NoticeManagerServiceImpl implements NoticeManagerService {

	@Resource(name = "NoticeManagerDaoImpl")
    NoticeManagerDao releaseNoticeDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Notice> getNoticeList() {
		return (List<Notice>) releaseNoticeDao.queryByHql(
				HqlNoticeManager.getNotice);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void setNotice(Notice notice) {
		releaseNoticeDao.saveOrUpdate(notice);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateNotice(NoticeForm noticeForm) {
		String hql = "update Notice set title=?,content=?,category=?,date=? where id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(noticeForm.getTitle());
		list.add(noticeForm.getContent());
		list.add(noticeForm.getCategory());
		list.add(new Date());
		list.add(Integer.parseInt(noticeForm.getNoticeId()));
		releaseNoticeDao.executeHqlUpdate(hql, list);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteNotice(NoticeForm form) {
		String hql = "delete from Notice where id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(Integer.parseInt(form.getNoticeId()));
		releaseNoticeDao.executeHqlUpdate(hql, list);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Notice> getNoticeInfo(NoticeForm form) {
		return (List<Notice>) releaseNoticeDao.queryByHql(
				HqlNoticeManager.getNoticeInfo,Integer.parseInt(form.getNoticeId()));
	}
	
	@Override
	@Transactional(readOnly=true)
	public Notice getProtocol() {
		List<Notice> protocolList = (List<Notice>)releaseNoticeDao.queryByHql(
				HqlNoticeManager.getNotice);
		return protocolList.get(0);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Notice getProtocolList() {
		List<Notice> protocolList = (List<Notice>)releaseNoticeDao.queryByHql(
				HqlNoticeManager.getProtocolList);
		return protocolList.get(0);
	}
}
