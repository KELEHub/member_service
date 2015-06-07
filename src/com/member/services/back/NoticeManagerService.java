package com.member.services.back;

import java.util.List;

import com.member.entity.Notice;

public interface NoticeManagerService {

	List<Notice> getNoticeList();

	void setNotice(Notice notice);

}
