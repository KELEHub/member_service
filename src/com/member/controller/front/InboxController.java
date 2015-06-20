package com.member.controller.front;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.member.entity.Tickling;
import com.member.services.front.InboxService;

@Controller
@RequestMapping(value = "/InboxController")
public class InboxController {

	@Resource(name = "InboxServiceImpl")
	public InboxService InboxService;

	@RequestMapping(value = "/showInbox",method = RequestMethod.POST)
	public String showInbox(Model model,HttpSession sesison){
		Object logonUserO = sesison.getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		String userNaemO = (String) logonUserMap.get("username");
		List<Tickling> result = InboxService.getInbox(userNaemO);
		model.addAttribute("result", result);
		return "front/memberNews/Inbox";
	}
}
