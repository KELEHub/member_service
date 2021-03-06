package com.member.controller.back;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.entity.Notice;
import com.member.form.back.NoticeForm;
import com.member.helper.BaseResult;
import com.member.services.back.NoticeManagerService;

@Controller
@RequestMapping(value = "/NoticeManagerController")
public class NoticeManagerController {

	@Resource(name = "NoticeManagerServiceImpl")
	public NoticeManagerService noticeManagerService;

	@RequestMapping(value = "/initNoticeEdit",method = RequestMethod.POST)
	public String initNoticeEdit(Model model){
		return "back/noticeManager/releaseNotice";
	}
	
	@RequestMapping(value = "/showNoticeManager",method = RequestMethod.POST)
	public String showNoticeManager(Model model){
		List<Notice> result = noticeManagerService.getNoticeList();
		model.addAttribute("result", result);
		return "back/noticeManager/noticeManager";
	}
	
	
	@RequestMapping(value = "/releaseNotice",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> releaseNotice(@RequestBody NoticeForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		Notice notice = new Notice();
		notice.setTitle(form.getTitle());
		notice.setCategory(form.getCategory());
		notice.setContent(form.getContent());
		notice.setDate(new Date());
		noticeManagerService.setNotice(notice);
		result.setMsg("发布公告成功.");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/editNotice",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> editNotice(@RequestBody NoticeForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		noticeManagerService.updateNotice(form);
		result.setMsg("修改公告成功.");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/deleteNotice",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> deleteNotice(@RequestBody NoticeForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		noticeManagerService.deleteNotice(form);
		result.setMsg("删除公告成功.");
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/getNoticeInfo",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> getNoticeInfo(@RequestBody NoticeForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		List<Notice> noticeList = noticeManagerService.getNoticeInfo(form);
		result.setElseExtend(noticeList.get(0));
		result.setSuccess(true);
		return result;
	}
	
}
