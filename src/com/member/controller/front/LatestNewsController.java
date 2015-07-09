package com.member.controller.front;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.member.entity.Notice;
import com.member.form.back.NoticeForm;
import com.member.form.front.ProtocolCheckForm;
import com.member.services.front.LatestNewsService;

@Controller
@RequestMapping(value = "/LatestNewsController")
public class LatestNewsController {

	@Resource(name = "LatestNewsServiceImpl")
	public LatestNewsService latestNewsService;

	@RequestMapping(value = "/showLatestNews",method = RequestMethod.POST)
	public String showLatestNews(Model model){
		List<Notice> result = latestNewsService.getLatestNewsList();
		if(result!=null){
			model.addAttribute("result", result);
		}
		return "front/memberNews/LatestNews";
	}
	
	@RequestMapping(value = "/getNews",method = RequestMethod.POST)
	public String getNews(@RequestBody NoticeForm form,Model model){
		List<Notice> result = latestNewsService.getNews(Integer.parseInt(form.getNoticeId()));
		model.addAttribute("result", result.get(0));
		return "front/memberNews/LatestNewsDetail";
	}
	
	@RequestMapping(value = "/showLatestNews2",method = RequestMethod.POST)
	public String showLatestNews2(@RequestBody ProtocolCheckForm form,Model model){
		List<Notice> result = latestNewsService.getLatestNewsList();
		if(result!=null){
			model.addAttribute("result", result);
		}
		return "front/memberNews/LatestNews";
	}
}
