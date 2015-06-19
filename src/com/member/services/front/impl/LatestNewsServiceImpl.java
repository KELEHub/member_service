package com.member.services.front.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.HqlNoticeManager;
import com.member.dao.LatestNewsDao;
import com.member.entity.Notice;
import com.member.services.front.LatestNewsService;

@Service("LatestNewsServiceImpl")
public class LatestNewsServiceImpl implements LatestNewsService {

	@Resource(name = "LatestNewsDaoImpl")
    LatestNewsDao latestNewsDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Notice> getLatestNewsList() {
		return (List<Notice>) latestNewsDao.queryByHql(
				HqlNoticeManager.getLatestNewsList);
	}
}
