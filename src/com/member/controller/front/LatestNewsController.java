package com.member.controller.front;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.member.entity.Notice;
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
}
