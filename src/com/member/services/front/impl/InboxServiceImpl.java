package com.member.services.front.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.member.dao.HqlTicklingManager;
import com.member.dao.InboxDao;
import com.member.entity.Tickling;
import com.member.services.front.InboxService;

@Service("InboxServiceImpl")
public class InboxServiceImpl implements InboxService {

	@Resource(name = "InboxDaoImpl")
    private InboxDao inboxDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Tickling> getInbox(String memberNumber) {
		return (List<Tickling>) inboxDao.queryByHql(
				HqlTicklingManager.getTicklingByMemberNumber,memberNumber);
	}
}
