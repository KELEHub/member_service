package com.member.services.back.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.HqlNoticeManager;
import com.member.dao.NoticeManagerDao;
import com.member.entity.Notice;
import com.member.services.back.NoticeManagerService;

@Service("ReleaseNoticeServiceImpl")
public class NoticeManagerServiceImpl implements NoticeManagerService {

	@Resource(name = "NoticeManagerDaoImpl")
    NoticeManagerDao releaseNoticeDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Notice> getNoticeByUserId(Integer userId) {
		return (List<Notice>) releaseNoticeDao.queryByHql(
				HqlNoticeManager.getNotice, userId);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void setNotice(Notice notice) {
		releaseNoticeDao.saveOrUpdate(notice);
	}
}
