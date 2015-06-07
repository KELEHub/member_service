package com.member.controller.back;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.entity.Tickling;
import com.member.form.back.TickForm;
import com.member.helper.BaseResult;
import com.member.services.back.TicklingManagerService;

@Controller
@RequestMapping(value = "/TicklingManagerController")
public class TicklingManagerController {

	@Resource(name = "TicklingManagerServiceImpl")
	public TicklingManagerService ticklingManagerService;

	@RequestMapping(value = "/showNotdoTicklingManager",method = RequestMethod.POST)
	public String showNotdoTicklingManager(Model model){
		List<Tickling> result = ticklingManagerService.getTicklingByState(0);
		model.addAttribute("result", result);
		return "back/ticklingManager/nodoTicklingManager";
	}
	
	@RequestMapping(value = "/showDoneTicklingManager",method = RequestMethod.POST)
	public String showDoneTicklingManager(Model model){
		List<Tickling> result = ticklingManagerService.getTicklingByState(1);
		model.addAttribute("result", result);
		return "back/ticklingManager/ticklingManager";
	}

	@RequestMapping(value = "/releaseNotice",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<Void> set(@RequestBody TickForm form,Model model){
		BaseResult<Void> result = new BaseResult<Void>();
		Tickling tickling = new Tickling();
		ticklingManagerService.setTickling(tickling);
		result.setMsg("处理留言成功.");
		result.setSuccess(true);
		return result;
	}
}
