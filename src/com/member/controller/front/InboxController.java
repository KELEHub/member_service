package com.member.controller.front;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.entity.Tickling;
import com.member.form.back.TickForm;
import com.member.helper.BaseResult;
import com.member.services.front.InboxService;

@Controller
@RequestMapping(value = "/InboxController")
public class InboxController {

	@Resource(name = "InboxServiceImpl")
	public InboxService inboxService;

	@RequestMapping(value = "/showInbox",method = RequestMethod.POST)
	public String showInbox(Model model,HttpSession sesison){
		Object logonUserO = sesison.getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		String userNaemO = (String) logonUserMap.get("username");
		List<Tickling> result = inboxService.getInbox(userNaemO);
		model.addAttribute("result", result);
		return "front/memberNews/Inbox";
	}
	
	@RequestMapping(value = "/releaseTickling",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> releaseTickling(@RequestBody TickForm form,Model model,HttpSession sesison){
		BaseResult<Void> result = new BaseResult<Void>();
		Object logonUserO = sesison.getAttribute("logonUser");
		Map<String, Object> logonUserMap = (Map<String, Object>) logonUserO;
		String userNaemO = (String) logonUserMap.get("username");
		int id = Integer.parseInt(inboxService.getInformationId(userNaemO).get(0).toString());
		Tickling tickling = new Tickling();
		tickling.setMemberId(id);
		tickling.setMemberNumber(userNaemO);
		tickling.setTitle(form.getTitle());
		tickling.setContent(form.getContent());
		tickling.setState(0);
		tickling.setTicklingDate(new Date());
		inboxService.saveOrUpdate(tickling);
		result.setMsg("发布成功");
		result.setSuccess(true);
		return result;
	}
}
