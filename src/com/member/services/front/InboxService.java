package com.member.services.front;

import java.util.List;

import com.member.entity.Tickling;

public interface InboxService {

	List<Tickling> getInbox(String memberNumber);

	List<Object> getInformationId(String memberNumber);

	void saveOrUpdate(Object entity);

}
