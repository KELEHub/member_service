package com.member.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/membermanage")
public class MemberManageController {

	@RequestMapping(value = "/showActivationMember",method = RequestMethod.POST)
	public String welcome(Model model) {
		return "back/membermanage/showactivemember";
	}
}
