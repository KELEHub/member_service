package com.member.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.helper.BaseResult;

@Controller
@RequestMapping(value = "/welcom")
public class WelcomController {

	@RequestMapping(value = "/testJsp")
	public String welcome(Model model) {
		return "back/index";
	}

	@RequestMapping(value = "/testJson")
	@ResponseBody
	public BaseResult<Void> welcomeJson(Model model) {
		BaseResult<Void> result = new BaseResult<Void>();
		result.setMsg("测试");
		result.setSuccess(true);
		return result;
	}
	
}
