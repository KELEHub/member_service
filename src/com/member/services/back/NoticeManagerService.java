package com.member.services.back;

import java.util.List;

import com.member.entity.Notice;
import com.member.form.back.NoticeForm;

public interface NoticeManagerService {

	List<Notice> getNoticeList();

	void setNotice(NoticeForm notice);

}
