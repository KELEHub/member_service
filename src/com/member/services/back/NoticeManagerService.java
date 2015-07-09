package com.member.services.back;

import java.util.List;

import com.member.entity.Notice;
import com.member.form.back.NoticeForm;

public interface NoticeManagerService {

	List<Notice> getNoticeList();

	void setNotice(Notice notice);

	void updateNotice(NoticeForm noticeForm);

	void deleteNotice(NoticeForm form);

	List<Notice> getNoticeInfo(NoticeForm form);

	Notice getProtocol();

}
