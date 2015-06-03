package com.member.services.back;

import java.util.List;

import com.member.entity.Notice;

public interface NoticeManagerService {

	List<Notice> getNoticeByUserId(Integer userId);

	void setNotice(Notice notice);

}
