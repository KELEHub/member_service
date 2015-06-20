package com.member.services.front.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.FAQDao;
import com.member.dao.HqlNoticeManager;
import com.member.entity.Notice;
import com.member.services.front.FAQService;

@Service("FAQServiceImpl")
public class FAQServiceImpl implements FAQService {

	@Resource(name = "FAQDaoImpl")
    FAQDao FAQDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Notice> getFAQ() {
		return (List<Notice>) FAQDao.queryByHql(
				HqlNoticeManager.getFAQ);
	}
}
