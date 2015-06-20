package com.member.controller.front;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.member.entity.Notice;
import com.member.services.front.FAQService;

@Controller
@RequestMapping(value = "/FAQController")
public class FAQController {

	@Resource(name = "FAQServiceImpl")
	public FAQService FAQService;

	@RequestMapping(value = "/showFAQ",method = RequestMethod.POST)
	public String showFAQ(Model model){
		List<Notice> result = FAQService.getFAQ();
		if(result!=null&&result.size()!=0){
			model.addAttribute("result", result.get(0));
		}
		return "front/memberNews/FAQ";
	}
}
